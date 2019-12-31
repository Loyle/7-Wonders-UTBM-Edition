package fr.utbm.swutbmedition.model.card;

import java.util.*;
import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public class Civil extends Facilities {

    private int creditsECTS;

    public Civil(String name,Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin, int creditECTS) {
        super(name,color,costMoney,costProduct,skin,nbPlayerMin);
        this.creditsECTS = creditECTS;
    }

	@Override
	protected void effect(Player p) {
		p.setCreditsECTS(p.getCreditsECTS()+this.creditsECTS);
		
	}

	@Override
	protected void effect() {
		// TODO Auto-generated method stub
		
	}

}