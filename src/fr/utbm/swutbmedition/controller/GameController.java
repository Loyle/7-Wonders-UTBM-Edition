package fr.utbm.swutbmedition.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import com.sun.xml.internal.bind.v2.model.core.ID;

import fr.utbm.swutbmedition.model.Game;
import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.model.card.Civil;
import fr.utbm.swutbmedition.model.card.Guild;
import fr.utbm.swutbmedition.model.card.ProductCard;
import fr.utbm.swutbmedition.model.loader.CardLoader;
import fr.utbm.swutbmedition.model.product.Food;
import fr.utbm.swutbmedition.model.product.Product;
import fr.utbm.swutbmedition.view.GameFrame;
//import sun.security.jca.GetInstance.Instance;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GameController {

    private GameFrame gameFrame;
    private Game game;
    
    private int playerID;


    public GameController(GameFrame gameFrame, Game game) {
        this.gameFrame = gameFrame;
        this.game = game;
        
        this.playerID = 0;
    }
    
    public void initGame(ArrayList<Player> players) {
    	this.game.setPlayers(players);
    	
    	// C'est ici qu'on va affecter les board / cartes aux joueurs
    
    	// Sachant qu'il faut permettre de pouvoir choisir la face de board sur laquel il souhaite commencer
    	
    	// Je fais les essaies ici du coup
    	for(Card card : this.game.getExistingCards()) {
        	//System.out.println(card.getName());
        }
    	
    	
    	this.game.setExistingCards(CardLoader.loadCard(this.game.getPlayers().size()));
    	// On donne les cartes de depart Ã  l'ensemble des joueurs
    	this.distributeCards();
    	this.giveBoard();
    	
    	for(Player p : this.game.getPlayers()) {
    		for(Card c : p.getHandCards()) {
    			//System.out.println(p.getName() + " => " + c.getName());    			
    		}
    	}
    }
    
    public void start() {
    	// Desactiver pour les essaies
    	if(this.game.getPlayers().size() < 2)
    		return;
    	
    	// melanger le tableau de player pour que l'ordre soit random
    	Collections.shuffle(this.game.getPlayers());
    	
    	this.playerID = 0;
    	this.game.setCurrentPlayer(this.game.getPlayers().get(playerID));
    	
    	this.gameFrame.refreshGameStatus();
    	this.gameFrame.refreshScoreboard();
    	
    	this.gameFrame.displayPlayerHand(this.game.getCurrentPlayer());
    	this.gameFrame.displayPlayerBoard(this.game.getCurrentPlayer());
    	this.gameFrame.refreshAllBoards();
    	
    	
    	this.game.setStart(true);
    }
    
    public void next() {
    	if(!this.game.isStart())
			return;
    	
    	this.playerID++;
    	
		if(this.playerID >= this.game.getPlayers().size()) {
			// On a fait un tour de tous les joueurs
			playerID = 0;
			this.game.setRound(this.game.getRound() + 1);
			
	    	this.gameFrame.refreshAllBoards();
			
			if(this.game.getRound() == 6) {
				// Il y a 6 tours par age
				this.game.setAge(this.game.getAge() + 1);
				this.game.setRound(0);
				// Faire les conflicts de fin d'age
				this.doConflicts();
				
				// On refresh les score
				this.gameFrame.refreshScoreboard();
				
				// On distribue les nouvelles cartes aux joueurs
				if(this.game.getAge() <= 3)
					this.distributeCards();
				
				// On change le sens de rotation
				if(this.game.getRotation() == 1)
					this.game.setRotation(0); // Right
				else
					this.game.setRotation(1); // Left
			}
			else {
				// On echange les paquets de cartes
				this.switchPlayersCards();
			}
		}
		
		if(this.checkEnd())
			this.finish();
		
		// On rÃ©cupÃ¨re le joueurs suivant
		this.game.setCurrentPlayer(this.game.getPlayers().get(this.playerID));
		
		// Refresh status game
		this.gameFrame.refreshGameStatus();

		// On affiche le board du joueurs suivant
		this.gameFrame.displayPlayerHand(this.game.getCurrentPlayer());
		this.gameFrame.displayPlayerBoard(this.game.getCurrentPlayer());
		
	}

	public void distributeCards() {
		// Distribue les cartes aux joueurs en fct de l'age
    	for(Player p : this.game.getPlayers()) {
    		// On clear la main actuelle
    		p.getHandCards().clear();
    		
    		ArrayList<Card> blacklist = new ArrayList<Card>();
    		
    		for(int j = 0; j < 7; j++) {
	    		// On distribu 7 cartes pour chaque joueurs (en fonction de l'Age)
	    		int i;
	    		do {
	    			Random r = new Random();
	    			i = r.nextInt(this.game.getExistingCards().size());
				} while (this.game.getExistingCards().get(i).getAge() != this.game.getAge() || blacklist.contains(this.game.getExistingCards().get(i)));
	    		
	    		blacklist.add(this.game.getExistingCards().get(i));
	    		p.addCard(this.game.getExistingCards().get(i));
    		}
    	}
    }
	
	public void giveBoard() {
		for(Player player : this.game.getPlayers()) {
			Random r = new Random();
			int i = r.nextInt(this.game.getExistingBoards().size());
			int j = (Math.random() < 0.5) ? 0 : 1;
			player.setBoard(this.game.getExistingBoards().get(i).get(0));
		}
		
		
	}
	
	private void switchPlayersCards() {
		if(this.game.getRotation() == 0) { // vers la droite
			ArrayList<Card> save = this.game.getPlayers().get(0).getHandCards();
			ArrayList<Card> temp = save;
			
			for(int i = 1; i < this.game.getPlayers().size(); i++) {
				temp = this.game.getPlayers().get(i).getHandCards();
				this.game.getPlayers().get(i).setHandCards(save);
				save = temp;
			}
			
			this.game.getPlayers().get(0).setHandCards(save);
		}
		else { // vers la gauche
			int last = this.game.getPlayers().size() - 1;
			ArrayList<Card> save = this.game.getPlayers().get(last).getHandCards();
			ArrayList<Card> temp = save;
			
			for(int i = last - 1; i >= 0; i--) {
				temp = this.game.getPlayers().get(i).getHandCards();
				this.game.getPlayers().get(i).setHandCards(save);
				save = temp;
			}
			
			this.game.getPlayers().get(last).setHandCards(save);
		}
	}
    
    private void doConflicts() {
 		// Victoire : 1 / 3 / 5 pts de victoire fct de l'age
    	// Defaire : -1
    	// Egalite : 0
    	// Check a droite et Ã  gauche
    	
    	for(Player p : this.game.getPlayers()) {
    		int pos = this.game.getPlayers().indexOf(p);
 
    		
    		
    		// Premier temps Ã  droite (donc + 1 par rapport Ã  numero du joueur)
    		int toCheck = pos + 1;
    		
    		if(pos == this.game.getPlayers().size() - 1)
    			toCheck = 0;
    		
    		if(p.getNumberFx() > this.game.getPlayers().get(toCheck).getNumberFx()) {
    			int toAdd = 1;
    			if(this.game.getAge() == 2)
    				toAdd = 3;
    			else if(this.game.getAge() == 3)
    				toAdd = 5;
    			
    			p.addConflicts(toAdd);
    		}
    		else if(p.getNumberFx() < this.game.getPlayers().get(toCheck).getNumberFx()) 
    			p.addConflicts(-1);
    		else 
    			p.addConflicts(0);
    		
    		
    		// Deuxieme temps Ã  gauche (donc + 1 par rapport Ã  numero du joueur)
    		toCheck = pos - 1;
    		
    		if(pos == 0)
    			toCheck = this.game.getPlayers().size() - 1;
    		
    		if(p.getNumberFx() > this.game.getPlayers().get(toCheck).getNumberFx()) {
    			int toAdd = 1;
    			if(this.game.getAge() == 2)
    				toAdd = 3;
    			else if(this.game.getAge() == 3)
    				toAdd = 5;
    			
    			p.addConflicts(toAdd);
    		}
    		else if(p.getNumberFx() < this.game.getPlayers().get(toCheck).getNumberFx())
    			p.addConflicts(-1);
    		else
    			p.addConflicts(0);
    	}
    	
 	}

    private boolean checkEnd() {
    	// Ici faut vérifier si c'est la fin de partie
    	if(this.game.getRound() == 0 && this.game.getAge() == 4) {
    		return true;    		
    	}
    	return false;
    }
    
    public void finish() {
		this.game.setStart(false);
		
		this.gameFrame.refreshScoreboard();
		
		this.gameFrame.getMainFrame().showMenuFrame();
		
		System.out.println("Partie terminée");
		
		this.gameFrame.finish();
    }
	
	private void checkDoubleProductCard(Card card) {
		if(card instanceof ProductCard) {
			ProductCard pCard = (ProductCard) card;
			
			if(pCard.getProducts().size() > 1) {
				if(pCard.getProducts().get(0).getClass().equals(pCard.getProducts().get(1).getClass()) == false) {
					int nb = (Math.random() < 0.5) ? 0 : 1;
					pCard.getProducts().remove(nb);
				}
			}
		}
	}
	
	public boolean useCard(Player player, Card card) {
		if(!this.game.isStart())
			return false;
		
		if(this.canUseCard(player, card)) {
			player.setMoney(player.getMoney() - card.getCostMoney());
			player.useCard(card);
			this.checkDoubleProductCard(card);
			this.next();
			
			return true;
		}
		return false;
	}
	
	public boolean canUseCard(Player player,Card card) {
		// Regarder si il a les ressources etc...
		// On check déjà  si c'est gratos
		if(card.getCostMoney() == 0 && card.getCostProduct().size() == 0) {
			return true;
		}
		// Ensuite cout en piece
		else if(card.getCostMoney() != 0 && card.getCostProduct().size() == 0) {
			if(player.getMoney() >= card.getCostMoney()) {
				return true;
			}
		}
		// Enfin les cout en ressources
		else {
			if(this.haveProductsToUse(player, card)) {
				return true;
			}
		}
				
		return false;
	}
	
	public boolean haveProductsToUse(Player player, Card card) {
		ArrayList<Product> usedProducts = new ArrayList<Product>();
		ArrayList<Product> playerProducts = player.getAllProducts();
		ArrayList<Product> notFind = new ArrayList<Product>();
		
		for(Product neededProd : card.getCostProduct()) {
			int i = 0;
			while(i < playerProducts.size() && playerProducts.get(i).getClass().equals(neededProd.getClass()) == false) {
				i++;
			}
			if(i < playerProducts.size()) {
				// We found a product !
				usedProducts.add(neededProd);
			}
			else {
				notFind.add(neededProd);
			}
		}
			
		/*if(!notFind.isEmpty()) {
			ArrayList<ArrayList<Product>> otherPlayerProducts = checkOtherProduct(this.game, player, notFind);
			for(ArrayList<Product> ap : otherPlayerProducts) {
				for(Product p : ap) {
					usedProducts.add(p);
				}
			}			
		}*/
		

		
		if(card.getCostProduct().size() == usedProducts.size()) {
			// We have all the product to use this card				
			return true;
		}
		else {
			return false;
		}
	}

	public void sellCard(Player player, Card card) {
		if(!this.game.isStart())
			return;
		
		player.sellCard(card);
		next();
	}
	
	public String buildWonder(Player player, Card card) {
		if(!this.game.isStart())
			return "la partie n'est pas lancée";
		if(true)
			return "";
		
		ArrayList<Product> usedProducts = new ArrayList<Product>();
		ArrayList<Product> playerProducts = player.getAllProducts();
		ArrayList<Product> notFind = new ArrayList<Product>();
		ArrayList<Product> costProduct = player.getBoard().getSteps().get(player.getBoard().getLevel()).getCostProduct();	
		if(playerProducts.size() > 0) {
			
			for(Product neededProd : costProduct) {
				if(neededProd != null) {
					int i = 0;
					while(i < playerProducts.size() && playerProducts.get(i).getClass().equals(neededProd.getClass()) == false) {
						i++;
					}
					if(i < playerProducts.size()) {
						// We found a product !
						usedProducts.add(neededProd);
						playerProducts.remove(i);
					}
					else {
						notFind.add(neededProd);
					}
				}
			}
						
			if(costProduct.size() == usedProducts.size()) {
				// We have all the product to step up the wonder
				player.getBoard().setLevel(player.getBoard().getLevel()+1);
				this.next();
			}
			else {
				String prodMiss = new String("Il vous manque des ressources !\n");
				for(Product p : notFind) {
					//prodMiss += "Besoin " + p.getClass().getName()+ "\n";
				}
				for(Product p : player.getAllProducts()) {						
					//System.out.println("Mes prods " + p.getClass().getName());
				}
				return prodMiss;
			}
		}
		next();
		return "";
	}

	
	public ArrayList<ArrayList<Product>> checkOtherProduct(Game g, Player p,ArrayList<Product>notFind) {
		
		ArrayList<Product> otherProductRight= new ArrayList<Product>();
		ArrayList<Product> otherProductLeft= new ArrayList<Product>();
		ArrayList<ArrayList<Product>> otherProductToGive = new ArrayList<ArrayList<Product>>();
		// Premier temps à droite (donc + 1 par rapport à numero du joueur)
		int pos = g.getPlayers().indexOf(p);
		int toCheck = pos + 1;
		
		if(pos == g.getPlayers().size() - 1)
			toCheck = 0;
		
		for(Product neededProd : notFind) {
			int i = 0;
			while(i < this.game.getPlayers().get(toCheck).getAllProducts().size() && this.game.getPlayers().get(toCheck).getAllProducts().get(i).getClass().equals(neededProd.getClass()) == false) {
				i++;
			}
			if(i < this.game.getPlayers().get(toCheck).getAllProducts().size()) {
				// We found a product !
					otherProductLeft.add(neededProd);
			}
		}
		otherProductToGive.add(otherProductRight);
		// Deuxième temps à gauche (donc + 1 par rapport à numero du joueur)
				toCheck = pos - 1;
				
				if(pos == 0)
					toCheck = g.getPlayers().size() - 1;
				for(Product neededProd : notFind) {
					if(!otherProductRight.contains(neededProd)) {
						int i = 0;
						
						while(i < this.game.getPlayers().get(toCheck).getAllProducts().size() && this.game.getPlayers().get(toCheck).getAllProducts().get(i).getClass().equals(neededProd.getClass()) == false) {
							i++;
						}
						if(i < this.game.getPlayers().get(toCheck).getAllProducts().size()) {
							// We found a product !
							otherProductLeft.add(neededProd);
						}
					}
					
				}
				otherProductToGive.add(otherProductLeft);
				if(notFind.size() == otherProductRight.size()) {
					// We have all the product to use this card				
					return otherProductToGive;
				}else {
					return null;
				}	
		
	}
}

