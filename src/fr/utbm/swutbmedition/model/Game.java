package fr.utbm.swutbmedition.model;

import java.util.*;

import fr.utbm.swutbmedition.model.board.Board;
import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.model.loader.BoardLoader;
import fr.utbm.swutbmedition.model.loader.CardLoader;

public class Game {

    //private GameFrame gameFrame;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private int round;
    private int age;
    private int rotation; // 0 => right, 1 => left
    private String ageName;  // 1: TC 2: SOC 3: BR <=> a voir si on s'en sert
    private boolean isStart;
    private ArrayList<Card> existingCards;
    private ArrayList<ArrayList<Board>> existingBoards;

    public Game() {
        //this.gameFrame = gameFrame;
        this.players = new ArrayList<Player>();
        this.currentPlayer = null;
        this.round = 0;
        this.age = 1;
        this.isStart = false;
        this.existingCards = CardLoader.loadCard(4);
        this.setExistingBoards(BoardLoader.loadBoard());
        this.rotation = 0;
    }
    
    public int getRound() {
    	return this.round;
    }
    public void setRound(int round) {
    	this.round = round;
    }
    
    public int getAge() {
    	return this.age;
    }
    public void setAge(int age) {
    	this.age = age;
    }
    
    public boolean isStart() {
    	return this.isStart;
    }
    public void setStart(boolean value) {
    	this.isStart = value;
    }
    
    public Player getCurrentPlayer() {
    	return this.currentPlayer;
    }
    public void setCurrentPlayer(Player player) {
    	this.currentPlayer = player;
    }
    
    public ArrayList<Player> getPlayers() {
    	return this.players;
    }
    public void addPlayer(Player player) {
    	this.players.add(player);
    }
    
    public ArrayList<Card> getExistingCards() {
    	return this.existingCards;
    }
    
    public void removeExistingCard(int i) {
    	this.existingCards.remove(i);
    }

	/**
	 * @return the rotation
	 */
	public int getRotation() {
		return rotation;
	}

	/**
	 * @param rotation the rotation to set
	 */
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public ArrayList<ArrayList<Board>> getExistingBoards() {
		return existingBoards;
	}

	public void setExistingBoards(ArrayList<ArrayList<Board>> existingBoards) {
		this.existingBoards = existingBoards;
	}
}