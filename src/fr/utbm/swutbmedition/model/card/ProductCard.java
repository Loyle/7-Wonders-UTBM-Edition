package fr.utbm.swutbmedition.model.card;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public class ProductCard extends Card {
	ArrayList<Product> products;
	
    public ProductCard(String name, int age, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin, ArrayList<Product> products) {
    	super(name,age,color,costMoney,costProduct,skin,nbPlayerMin);
    	this.products = products;
    }
    
    public ArrayList<Product> getProducts() {
    	return this.products;
    }
    
    protected void effect() {
	}

	@Override
	protected void effect(Player p) {
		
	}
	
	@Override
	public Card copy() {
		return new ProductCard(name, age, color, costMoney, costProduct, skin, nbPlayerMin,this.products);
	}
}