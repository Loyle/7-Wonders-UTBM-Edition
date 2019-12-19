package fr.utbm.swutbmedition.model.card;

import java.awt.Color;
import java.util.*;

import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.product.Product;

public abstract class Facilities extends Card {

    protected ArrayList<Facilities> nextFacilities;

    public Facilities() {
        // TODO implement here
    }

    public Facilities(String name, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin) {
        // TODO implement here
    }

    protected abstract void effect();

}