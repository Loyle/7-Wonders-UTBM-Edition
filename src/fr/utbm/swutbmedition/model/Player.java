package fr.utbm.swutbmedition.model;

import java.util.*;

import fr.utbm.swutbmedition.model.board.Board;
import fr.utbm.swutbmedition.model.card.Card;

public class Player {
    private String name;
    private int score;
    private ArrayList<Card> usedCard;
    private ArrayList<Card> currentCard;
    private Board board;
    private int money;
    private int fx;
    private int creditsECTS;

    public Player() {
        this.name = "";
        this.score = 0;
        this.usedCard = new ArrayList<Card>();
        this.currentCard = new ArrayList<Card>();
        this.board = new Board();
        this.money = 0;
        this.fx = 0;
        this.setCreditsECTS(0);
    }
    public Player(String name) {
    	this.name = "";
        this.score = 0;
        this.usedCard = new ArrayList<Card>();
        this.currentCard = new ArrayList<Card>();
        this.board = new Board();
        this.money = 0;
        this.fx = 0;
        this.setCreditsECTS(0);
    }
    
    public void addCard(Card card) {
    	this.currentCard.add(card);
    }
    public boolean useCard(Card card) {
    	if(this.currentCard.contains(card)) {
    		this.currentCard.remove(card);
    		this.usedCard.add(card);
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    public void countScore() {
        int scientificScore;
    	for(Card card: this.usedCard) {
    		if(card.getClass().equals());
    	}
    	
    }
	public int getCreditsECTS() {
		return creditsECTS;
	}
	public void setCreditsECTS(int creditsECTS) {
		this.creditsECTS = creditsECTS;
	}

}