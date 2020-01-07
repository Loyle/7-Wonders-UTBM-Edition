package fr.utbm.swutbmedition.model;

import java.net.InterfaceAddress;
import java.util.*;

import fr.utbm.swutbmedition.model.board.Board;
import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.model.card.Civil;
import fr.utbm.swutbmedition.model.card.Commercial;
import fr.utbm.swutbmedition.model.card.Guild;
import fr.utbm.swutbmedition.model.card.Military;
import fr.utbm.swutbmedition.model.card.ProductCard;
import fr.utbm.swutbmedition.model.card.Scientific;
import fr.utbm.swutbmedition.model.product.Product;

public class Player {
    private String name;
    private int score;
    private ArrayList<Card> usedCards;
    private ArrayList<Card> handCards;
    private Board board;
    private int money;
    private int creditsECTS;
    private ArrayList<Integer> conflicts;

    public Player() {
        this.name = "";
        this.score = 0;
        this.usedCards = new ArrayList<Card>();
        this.handCards = new ArrayList<Card>();
        this.board = new Board();
        this.money = 3;
        this.creditsECTS = 0;
        this.conflicts = new ArrayList<Integer>();
    }
    public Player(String name) {
    	this.name = name;
        this.score = 0;
        this.usedCards = new ArrayList<Card>();
        this.handCards = new ArrayList<Card>();
        this.board = new Board();
        this.money = 3;
        this.creditsECTS = 0;
        this.conflicts = new ArrayList<Integer>();
    }
    
    public void addCard(Card card) {
    	this.handCards.add(card);
    }
    public boolean useCard(Card card) {
    	if(this.handCards.contains(card)) {
    		this.handCards.remove(card);
    		this.usedCards.add(card);
    		return true;
    	}
    	return false;
    }

    public void countScore(Game game) {
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
        int nbCs = 0, nbTm=0,nbCg=0;
        ArrayList<Integer> symbole = null;
    	for(Card card: this.usedCards) {
    		if(card instanceof Civil) {
    			// les cartes civils on des pts (credit ECTS)
    			civilPoints += ((Civil) card).getCreditsECTS();
    		}
    		else if(card instanceof Scientific) {
    			// Pt de cartes scientifique : Famille de symbole identiques et Groupe de 3 symboles différents
    			// Donc ici on va juste stocker les symboles des cartes, on fera le calcul après la boucle for
    			
    			if(((Scientific)card).getSymbole().equals("CS")) {
    				symbole.add(++nbCs);
    			}else if(((Scientific)card).getSymbole().equals("TM")) {
    				symbole.add(++nbTm);
    			}else if(((Scientific)card).getSymbole().equals("CG")) {
    				symbole.add(++nbCg);
    			}
    		}
    		else if(card instanceof Commercial) {
    			// Les cartes commercials d'age III rapporte 2 points chacune
    			if(card.getAge() == 3)
    				commercialPoints += 2;
    		}
    		else if(card instanceof Guild) {
    			Guild guild = (Guild) card;
    			guild.effect(game, this);
    			// Depend de ses voisin etc... CHIANT
    		}
    	}
    	
    	Integer var = symbole.stream().min(Integer::compare).get(); 
    	scientificPoints= nbCg*nbCg+nbCs*nbCs+nbTm*nbTm+7*var;
    	this.creditsECTS = conflictsPoints + treasurePoints + wonderPoints + civilPoints + scientificPoints + commercialPoints + guildPoints;
    }
    public ArrayList<Product> getAllProducts() {
    	ArrayList<Product> products = new ArrayList<Product>();
    	
    	// On récupère le product du board
    	products.add(this.board.getProduct());
    	
    	// FAUT RECUPERER AUSSI LES PRODUCT DES ETAPES DE BOARD
    	
    	for(Card card : this.usedCards) {
    		
    		if (card instanceof ProductCard) {
    			// On récupère les products de toutes les cards
				products.addAll(((ProductCard) card).getProducts());
			}
    	}
    	
    	return products;
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
	public String getName() {
		return this.name;
	}
	public ArrayList<Card> getHandCards() {
		return this.handCards;
	}
	
	public ArrayList<Card> getUsedCard(){
		return this.usedCards;
	}
	public void setHandCards(ArrayList<Card> handCards) {
		this.handCards = handCards;
	}
	public int getMoney() {
		return this.money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getNumberFx() {
		int total = 0;
		
		for(Card card : this.usedCards) {
			if (card instanceof Military) {
				total += ((Military) card).getFx();
			}
		}
		return total;
	}
	public ArrayList<Integer> getConflicts() {
		// TODO Auto-generated method stub
		return this.conflicts;
	}
	public Board getBoard() {
		// TODO Auto-generated method stub
		return this.board;
	}
}