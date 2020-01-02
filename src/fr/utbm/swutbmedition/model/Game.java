package fr.utbm.swutbmedition.model;

import java.util.*;

import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.model.loader.CardLoader;

public class Game {

    //private GameFrame gameFrame;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private int round;
    private int age;
    private String ageName;  // 1: TC 2: SOC 3: BR <=> a voir si on s'en sert
    private boolean isStart;
    private ArrayList<Card> existingCards;

    public Game() {
        //this.gameFrame = gameFrame;
        this.players = new ArrayList<Player>();
        this.currentPlayer = null;
        this.round = 0;
        this.age = 1;
        this.isStart = false;
        this.existingCards = CardLoader.loadCard();
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
}