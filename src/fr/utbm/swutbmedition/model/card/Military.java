package fr.utbm.swutbmedition.model.card;

import java.util.*;
import javax.swing.ImageIcon;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public class Military extends Facilities {
    private int shield;

    public Military(String name, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin, int shield) {
    	super(name,color,costMoney,costProduct,skin,nbPlayerMin);
    	this.shield = shield;
    }


	@Override
	protected void effect() {
		// TODO Auto-generated method stub
		
	}
}