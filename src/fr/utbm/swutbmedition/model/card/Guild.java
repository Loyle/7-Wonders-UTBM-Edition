package fr.utbm.swutbmedition.model.card;

import java.util.ArrayList;
import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public class Guild extends Card {

    public Guild(String name, int age, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin) {
    	super(name,age,color,costMoney,costProduct,skin,nbPlayerMin);
    }
    
    public void addPlayer() {
        // TODO implement here
    }

    protected void effect() {
	}

	@Override
	protected void effect(Player p) {
		// TODO Auto-generated method stub
		
	}

}