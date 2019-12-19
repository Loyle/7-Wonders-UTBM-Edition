package fr.utbm.swutbmedition.model.card;

import java.awt.Color;
import java.util.*;

import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.product.Product;

public abstract class Card {

    public Card() {
    }

    protected String name;

    protected Color color;

    protected int costMoney;

    protected ArrayList<Product> costProduct;

    protected ImageIcon skin;

    protected int nbPlayerMin;

    protected abstract void effect();

    public void Card() {
        // TODO implement here
    }

    public void Card(String name, Color color, int costMoney, Product ArrayList costProduct, ImageIcon ImageIcon skin, int int nbPlayerMin) {
        // TODO implement here
    }

}