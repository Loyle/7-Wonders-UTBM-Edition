package fr.utbm.swutbmedition.model.board;
import java.util.*;

import fr.utbm.swutbmedition.model.product.Product;

public class Step {

    private ArrayList<Product> costProduct;

    private ArrayList<Effect> effects;
    
    public Step() {
        this.setCostProduct(null);
        this.effects = null;
    }
    
    public Step(ArrayList<Product> costProduct, ArrayList<Effect> effects) {
    	
    	this.setCostProduct(costProduct);
    	this.effects = effects;
    	
    }

	public ArrayList<Product> getCostProduct() {
		return costProduct;
	}

	public void setCostProduct(ArrayList<Product> costProduct) {
		this.costProduct = costProduct;
	}
}