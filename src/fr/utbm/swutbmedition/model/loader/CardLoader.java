package fr.utbm.swutbmedition.model.loader;

import java.util.*;

import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.model.card.Civil;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public class CardLoader {
	
    public static ArrayList<Card> loadCard() {
        ArrayList<Card> cards = new ArrayList<Card>();
        
        // Ouvrir le fichier excel et creer les cartes
        for(int i = 0; i < 10; i++) {
        	cards.add(new Civil("Ehoyio", Color.AZURE, 0, new ArrayList<Product>(), new ImageIcon(), 0, 5));        	
        }
        
        return cards;
    }

}