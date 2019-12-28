package fr.utbm.swutbmedition.model;

import java.util.*;

import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.model.loader.CardLoader;
import fr.utbm.swutbmedition.view.GameFrame;

public class Game {

    private GameFrame gameFrame;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private int round;
    private boolean isStart;
    private ArrayList<Card> existingCards;

    public Game(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.players = new ArrayList<Player>();
        this.currentPlayer = null;
        this.round = 0;
        this.isStart = false;
        this.existingCards = CardLoader.loadCard();
    }
    
    public void addPlayer(Player player) {
    	this.players.add(player);
    }
    
    public void start() {
    	//if(this.players.size() < 2)
    	//	return;
    	
    	this.initGame();
    	
    	// Voir pour melanger le tableau de player pour que l'ordre soit random
    	int playerID = 0;
    	this.currentPlayer = this.players.get(playerID);
    	
    	this.isStart = true;
    	
    	while(this.isStart) {
    		
    		this.gameFrame.displayBoard(this.currentPlayer);
    		
    		
    		
    		if(this.checkEnd())
    			this.isStart = false;
    		
    		playerID++;
    		if(playerID >= this.players.size()) {
    			// On a fait un tour de tous les joueurs
    			playerID = 0;
    			this.round++;
    		}
    	}
    	
    	// ici declencher les actions de fin de game
    }
    
    private void initGame() {
    	// C'est ici qu'on va affecter les board / cartes aux joueurs
    	// Sachant qu'il faut permettre de pouvoir choisir la face de board sur laquel il souhaite commencer
    	
    	// Je fais les essaies ici du coup
    	for(Card card : this.existingCards) {
        	System.out.println(card.getName());
        }
    	this.addPlayer(new Player("Marco"));
    	this.addPlayer(new Player("Louis"));
    	this.addPlayer(new Player("Theo"));
    	this.addPlayer(new Player("Antoine"));
    	
    	// On donne les cartes de depart � l'ensemble des joueurs
    	for(Player p : this.players) {
    		
    		//p.addCard();
    	}
    }
    
    private boolean checkEnd() {
    	// Ici faut v�rifier si c'est la fin de partie
    	return true;
    }
    
    public int getRound() {
    	return this.round;
    }
}