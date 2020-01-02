package fr.utbm.swutbmedition.model.card;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public class ProductCard extends Card {
    private Product product;


    public ProductCard(String name, int age, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin) {
    	super(name,age,color,costMoney,costProduct,skin,nbPlayerMin);
    	this.product = new Product();
    }

    protected void effect() {
	}

	@Override
	protected void effect(Player p) {
		
	}
	
	@Override
	public Card copy() {
		return new ProductCard(name, age, color, costMoney, costProduct, skin, nbPlayerMin);
	}
}