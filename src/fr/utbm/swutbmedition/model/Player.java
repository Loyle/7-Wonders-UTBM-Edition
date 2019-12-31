package fr.utbm.swutbmedition.model;

import java.util.*;

import fr.utbm.swutbmedition.model.board.Board;
import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.model.card.Civil;
import fr.utbm.swutbmedition.model.card.Commercial;
import fr.utbm.swutbmedition.model.card.Guild;
import fr.utbm.swutbmedition.model.card.Scientific;

public class Player {
    private String name;
    private int score;
    private ArrayList<Card> usedCard;
    private ArrayList<Card> currentCard;
    private Board board;
    private int money;
    private int fx;
    private int creditsECTS;
    private ArrayList<Integer> conflicts;

    public Player() {
        this.name = "";
        this.score = 0;
        this.usedCard = new ArrayList<Card>();
        this.currentCard = new ArrayList<Card>();
        this.board = new Board();
        this.money = 0;
        this.fx = 0;
        this.creditsECTS = 0;
        this.conflicts = new ArrayList<Integer>();
    }
    public Player(String name) {
    	this.name = "";
        this.score = 0;
        this.usedCard = new ArrayList<Card>();
        this.currentCard = new ArrayList<Card>();
        this.board = new Board();
        this.money = 0;
        this.fx = 0;
        this.creditsECTS = 0;
        this.conflicts = new ArrayList<Integer>();
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
    	
    	// Les pts de conflits sont converti en pt de victoire
    	int conflictsPoints = 0;
        for(int points : this.conflicts) {
        	conflictsPoints += points;
        }
        
        
        // Toutes les 3 pièces = 1 pt de score
        int treasurePoints = 0;
        treasurePoints = (int) (this.money / 3);
        
        // En fonction de la face et de l'étape de construction, on recupère les pt de merveille
        int wonderPoints = 0;
        wonderPoints = this.board.getCreditsECTS();
        
        // On récupère les points des cartes jouées
        int civilPoints = 0;
        int scientificPoints = 0;
        int commercialPoints = 0;
        int guildPoints = 0;
        
    	for(Card card: this.usedCard) {
    		if(card instanceof Civil) {
    			// les cartes civils on des pts (credit ECTS)
    			civilPoints += ((Civil) card).getCreditsECTS();
    		}
    		else if(card instanceof Scientific) {
    			// Pt de cartes scientifique : Famille de symbole identiques et Groupe de 3 symboles différents
    			scientificPoints += 0;
    			// Donc ici on va juste stocker les symboles des cartes, on fera le calcul après la boucle for
    		}
    		else if(card instanceof Commercial) {
    			// Les cartes commercials d'age III rapporte 2 points chacune
    			if(card.getAge() == 3)
    				commercialPoints += 2;
    		}
    		else if(card instanceof Guild) {
    			// Depend de ses voisin etc... CHIANT
    		}
    	}
    	this.creditsECTS = conflictsPoints + treasurePoints + wonderPoints + civilPoints + scientificPoints + commercialPoints + guildPoints;
    }
	public int getCreditsECTS() {
		return creditsECTS;
	}
	public void setCreditsECTS(int creditsECTS) {
		this.creditsECTS = creditsECTS;
	}
	public void addConflicts(int result) {
		this.conflicts.add(result);
	}
}