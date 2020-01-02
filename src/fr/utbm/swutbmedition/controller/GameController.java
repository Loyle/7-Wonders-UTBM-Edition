package fr.utbm.swutbmedition.controller;

import java.util.Random;

import fr.utbm.swutbmedition.model.Game;
import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.view.GameFrame;

public class GameController {

    private GameFrame gameFrame;
    private Game game;


    public GameController(GameFrame gameFrame, Game game) {
        this.gameFrame = gameFrame;
        this.game = game;
    }
    
    public void start() {
    	// Desactiver pour les essaies
    	//if(this.players.size() < 2)
    	//	return;
    	
    	this.initGame();
    	
    	// Voir pour melanger le tableau de player pour que l'ordre soit random
    	int playerID = 0;
    	this.game.setCurrentPlayer(this.game.getPlayers().get(playerID));
    	
    	this.game.setStart(true);
    	
    	while(this.game.isStart()) {
    		
    		this.gameFrame.displayBoard(this.game.getCurrentPlayer());
    		
    		
    		playerID++;
    		if(playerID >= this.game.getPlayers().size()) {
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
    				
    				// On distribue les nouvelles cartes aux joueurs
    				if(this.game.getAge() <= 3)
    					this.distributeCards();
    			}
    		}
    		
    		if(this.checkEnd())
    			this.game.setStart(false);
    	}
    	
    	// Partie finie !
    	// ici declencher les actions de fin de game
    	
    	System.out.println("Partie terminée");
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
    			System.out.println(p.getName() + " => " + c.getName());    			
    		}
    	}
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
    
    private void doConflicts() {
 		// Voir comment faire ca
 	}

    private boolean checkEnd() {
    	// Ici faut vérifier si c'est la fin de partie
    	if(this.game.getRound() == 0 && this.game.getAge() == 4) {
    		return true;    		
    	}
    	return false;
    }
}