package fr.utbm.swutbmedition.model.card;

import java.util.*;

import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public abstract class Card {
	
    protected String name;
    protected Color color;
    protected int costMoney;
    protected ArrayList<Product> costProduct;
    protected ImageIcon skin;
    protected int nbPlayerMin;


    public Card() {
    	this.name = "";
        this.color = Color.TRANSPARENT;
        this.costMoney = 0;
        this.costProduct = new ArrayList<Product>();
        this.skin = new ImageIcon();
        this.nbPlayerMin = 0;
    }
    public Card(String name, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin) {
        this.name = name;
        this.color = color;
        this.costMoney = costMoney;
        this.costProduct= costProduct;
        this.skin = skin;
        this.nbPlayerMin = nbPlayerMin;
    }

    protected abstract void effect();
}