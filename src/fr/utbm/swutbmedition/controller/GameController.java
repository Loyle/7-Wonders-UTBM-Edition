package fr.utbm.swutbmedition.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import fr.utbm.swutbmedition.model.Game;
import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.model.product.Food;
import fr.utbm.swutbmedition.model.product.Product;
import fr.utbm.swutbmedition.view.GameFrame;
import sun.security.jca.GetInstance.Instance;

public class GameController {

    private GameFrame gameFrame;
    private Game game;
    
    private int playerID;


    public GameController(GameFrame gameFrame, Game game) {
        this.gameFrame = gameFrame;
        this.game = game;
        
        this.playerID = 0;
    }
    
    public void initGame() {
    	// C'est ici qu'on va affecter les board / cartes aux joueurs
    	// Sachant qu'il faut permettre de pouvoir choisir la face de board sur laquel il souhaite commencer
    	
    	// Je fais les essaies ici du coup
    	for(Card card : this.game.getExistingCards()) {
        	//System.out.println(card.getName());
        }
    	this.game.addPlayer(new Player("Marco"));
    	this.game.addPlayer(new Player("Louis"));
    	this.game.addPlayer(new Player("Theo"));
    	this.game.addPlayer(new Player("Antoine"));
    	
    	// On donne les cartes de depart à l'ensemble des joueurs
    	this.distributeCards();
    	
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
    	
    	this.gameFrame.displayBoard(this.game.getCurrentPlayer());
    	
    	
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
			
			System.out.println("Round : " + this.game.getRound() + " | Age : " + this.game.getAge());
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
		
		// On récupère le joueurs suivant
		this.game.setCurrentPlayer(this.game.getPlayers().get(this.playerID));
		
		// Refresh status game
		this.gameFrame.refreshGameStatus();

		// On affiche le board du joueurs suivant
		this.gameFrame.displayBoard(this.game.getCurrentPlayer());
		
	}

	public void distributeCards() {
    	// Distribue les cartes aux joueurs en fct de l'age
    	for(Player p : this.game.getPlayers()) {
    		// On clear la main actuelle
    		p.getHandCards().clear();
    		
    		for(int j = 0; j < 7; j++) {
	    		// On distribu 7 cartes pour chaque joueurs (en fonction de l'Age)
	    		int i;
	    		do {
	    			Random r = new Random();
	    			i = r.nextInt(this.game.getExistingCards().size());
				} while (this.game.getExistingCards().get(i).getAge() != this.game.getAge());
	    		
	    		
	    		p.addCard(this.game.getExistingCards().get(i).copy());
    		}
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
    	// Egalité : 0
    	// Check a droite et à gauche
    	
    	for(Player p : this.game.getPlayers()) {
    		int pos = this.game.getPlayers().indexOf(p);
 
    		
    		
    		// Premier temps à droite (donc + 1 par rapport à numero du joueur)
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
    		
    		
    		// Deuxième temps à gauche (donc + 1 par rapport à numero du joueur)
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
		
		System.out.println("Partie terminée");
    }

	public void useCard(Player player, Card card) {
		if(!this.game.isStart())
			return;
		
		// Regarder si il a les ressources etc...
		
		// On check déjà si c'est gratos
		if(card.getCostMoney() == 0 && card.getCostProduct().size() == 0) {
			player.useCard(card);			
			this.next();
		}
		// Ensuite cout en piece
		else if(card.getCostMoney() != 0 && card.getCostProduct().size() == 0) {
			if(player.getMoney() >= card.getCostMoney()) {
				player.setMoney(player.getMoney() - card.getCostMoney());
				player.useCard(card);			
				this.next();
			}
		}
		// Enfin les cout en ressources
		else {
			ArrayList<Product> usedProducts = new ArrayList<Product>();
			ArrayList<Product> playerProducts = player.getAllProducts();
			
			if(playerProducts.size() > 0) {
				for(Product neededProd : card.getCostProduct()) {
					int i = 0;
					while(i < playerProducts.size() && playerProducts.get(i).getClass().equals(neededProd.getClass()) == false) {
						i++;
					}
					if(i < playerProducts.size()) {
						// We found a product !
						usedProducts.add(neededProd);
					}
				}
				
				if(card.getCostProduct().size() == usedProducts.size()) {
					// We have all the product to use this card
					player.useCard(card);
					this.next();
				}
				else {
					System.out.println("Il vous manque des ressources !");
				}
			}
		}
	}
}