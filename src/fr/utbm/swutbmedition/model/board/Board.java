package fr.utbm.swutbmedition.model.board;
import java.util.*;

import fr.utbm.swutbmedition.model.product.Product;

public class Board {
    private String name;
    private Product product;
    private int level; // step actuel de la w 
    private ArrayList<Step> steps;
    private String face;
    
    public Board() {
    	this.face= "";
    	this.name = "";
    	this.product = new Product();
    	this.level = 0;
    	this.steps = new ArrayList<Step>();
    }
    public Board(String name, String face, Product product, ArrayList<Step> steps) {
    	this.face = face;
    	this.name = name;
    	this.product = product;
    	this.level = 0;
    	this.steps = steps;
    }
    
    public int getCreditsECTS() {
    	int credits = 0;
    	for(int i = 0; i <= level; i++) {
    		//this.steps[i].getEffect();
    		credits += 1;
    	}
    	
    	return credits;
    }
    public Product getProduct() {
    	return this.product;
    }
    
    public int getLevel() {
		return this.level;
    	
    }
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public ArrayList<Step> getSteps() {
		return steps;
	}
	public void setSteps(ArrayList<Step> steps) {
		this.steps = steps;
	}

}