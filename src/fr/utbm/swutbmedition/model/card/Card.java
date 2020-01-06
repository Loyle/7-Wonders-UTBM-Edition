package fr.utbm.swutbmedition.model.card;

import java.util.*;

import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public abstract class Card {
	
    protected String name;
    protected Color color;
    protected int costMoney;
    protected ArrayList<Product> costProduct;
    protected ImageIcon skin;
    protected int nbPlayerMin;
    protected int age;


    public Card() {
    	this.name = "";
        this.color = Color.TRANSPARENT;
        this.costMoney = 0;
        this.costProduct = new ArrayList<Product>();
        this.skin = new ImageIcon();
        this.nbPlayerMin = 0;
        this.age = 1;
    }
    public Card(String name, int age, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.costMoney = costMoney;
        this.costProduct= costProduct;
        this.skin = skin;
        this.nbPlayerMin = nbPlayerMin;
    }
    public String getName() {
		return this.name;
	}
    public int getAge() {
    	return this.age;
    }
    public int getCostMoney() {
    	return this.costMoney;
    }
    public ArrayList<Product> getCostProduct() {
    	return this.costProduct;
    }
    public Color getColor() {
		return this.color;
	}
    
    protected abstract void effect();
	protected abstract void effect(Player p);
	public abstract Card copy();
}