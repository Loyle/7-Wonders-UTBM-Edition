package fr.utbm.swutbmedition.model.card;

import java.util.ArrayList;
import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public class Scientific extends Facilities {
    private String symbole;

    public Scientific(String name, int age, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin, String symbole) {
    	super(name,age,color,costMoney,costProduct,skin,nbPlayerMin);
    	this.symbole = symbole;
    }


	@Override
	protected void effect() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void effect(Player p) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Card copy() {
		return new Scientific(name, age, color, costMoney, costProduct, skin, nbPlayerMin, symbole);
	}
	
	public String getSymbole() {
		return this.symbole;
	}
}