package fr.utbm.swutbmedition.model.card;


import java.util.*;
import javax.swing.ImageIcon;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public class Commercial extends Facilities {

    public Commercial(String name, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin) {
    	super(name,color,costMoney,costProduct,skin,nbPlayerMin);
    }

	@Override
	protected void effect() {
		// TODO Auto-generated method stub
		
	}
}