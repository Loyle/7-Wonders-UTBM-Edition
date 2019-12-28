package fr.utbm.swutbmedition.model.card;

import java.util.*;
import javax.swing.ImageIcon;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public abstract class Facilities extends Card {

    protected ArrayList<Facilities> nextFacilities;

    public Facilities(String name, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin) {
        super(name,color,costMoney,costProduct,skin,nbPlayerMin);
    }

    protected abstract void effect();

}