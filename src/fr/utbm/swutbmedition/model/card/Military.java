package fr.utbm.swutbmedition.model.card;

import java.util.*;
import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public class Military extends Facilities {
    private int fx;

    public Military(String name, int age, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin, int fx) {
    	super(name,age,color,costMoney,costProduct,skin,nbPlayerMin);
    	this.fx = fx;
    }


	@Override
	protected void effect() {
			
	}


	@Override
	protected void effect(Player p) {
			
	}
	
	public int getFx() {
		return this.fx;
	}
	
	@Override
	public Card copy() {
		return new Military(name, age, color, costMoney, costProduct, skin, nbPlayerMin, fx);
	}
}