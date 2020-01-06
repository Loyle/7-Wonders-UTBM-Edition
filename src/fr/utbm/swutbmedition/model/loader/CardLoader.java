package fr.utbm.swutbmedition.model.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.model.card.Civil;
import fr.utbm.swutbmedition.model.card.Commercial;
import fr.utbm.swutbmedition.model.card.Guild;
import fr.utbm.swutbmedition.model.card.Military;
import fr.utbm.swutbmedition.model.card.ProductCard;
import fr.utbm.swutbmedition.model.card.Scientific;
import fr.utbm.swutbmedition.model.product.Book;
import fr.utbm.swutbmedition.model.product.Computer;
import fr.utbm.swutbmedition.model.product.Desk;
import fr.utbm.swutbmedition.model.product.Drink;
import fr.utbm.swutbmedition.model.product.Food;
import fr.utbm.swutbmedition.model.product.Pen;
import fr.utbm.swutbmedition.model.product.Product;
import fr.utbm.swutbmedition.model.product.Sheet;
import javafx.scene.paint.Color;

public class CardLoader {
	
    public static ArrayList<Card> loadCard() {
        ArrayList<Card> cards = new ArrayList<Card>();
        
        String csvFile = "./data/cards/CardFile.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        
        try {
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine(); // On lit la première ligne qui est la ligne d'entête
			while ((line = br.readLine()) != null) {
                
                // use comma as separator
                String[] split = line.split(cvsSplitBy);
                                
                
                ArrayList<Product> products = new ArrayList<Product>();
                for(int i = 8; i <= 14; i++) {
                	// On ajout les product
                	if(!split[i].equals("-")) {
                		int nb = Integer.valueOf(split[i]);
                		
                		for(int j = 0; j < nb; j++) {
	                		Product product;
	                		switch (i) {
							case 8:
								product = new Food();
								break;
							case 9:
								product = new Drink();
								break;
							case 10:
								product = new Pen();
								break;
							case 11:
								product = new Sheet();
								break;
							case 12:
								product = new Computer();
								break;
							case 13:
								product = new Book();
								break;
							case 14:
								product = new Desk();
								break;
							default:
								product = null;
								break;
							}
	                		products.add(product);
                		}
                	}
                }
                
                // Faire peut etre 3 ArrayList<Card> et les classer selon l'age d'apparition de la card 
                String effect = split[7];
                if(effect.equals("-"))
                	effect = "0";
                
                // AGE
                int age = 1; // TC
                if(split[6].equals("SOC")) // SOC
                	age = 2;
                else if(split[6].equals("BR")) // BR
                	age = 3;
                
                
                switch (split[1]) {
				case "CardProduct":
					ArrayList<Product> prods = CardLoader.loadProducts(effect);
					cards.add(new ProductCard(split[2], age, Color.web(split[3]), Integer.valueOf(split[4]), products, new ImageIcon(), Integer.valueOf(split[5]), prods));
					break;
				case "Civil":
					cards.add(new Civil(split[2], age, Color.web(split[3]), Integer.valueOf(split[4]), products, new ImageIcon(), Integer.valueOf(split[5]), Integer.valueOf(effect)));
					break;
				case "Commercial":
					cards.add(new Commercial(split[2], age, Color.web(split[3]), Integer.valueOf(split[4]), products, new ImageIcon(), Integer.valueOf(split[5])));
					break;
				case "Military":
					cards.add(new Military(split[2], age, Color.web(split[3]), Integer.valueOf(split[4]), products, new ImageIcon(), Integer.valueOf(split[5]), Integer.valueOf(effect)));					
					break;
				case "Scientific":
					cards.add(new Scientific(split[2], age, Color.web(split[3]), Integer.valueOf(split[4]), products, new ImageIcon(), Integer.valueOf(split[5]), effect));					
					break;
				case "Guild":
					cards.add(new Guild(split[2], age, Color.web(split[3]), Integer.valueOf(split[4]), products, new ImageIcon(), Integer.valueOf(split[5])));					
					break;					
				default:
					break;
				}

            }
			
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return cards;
    }
    private static ArrayList<Product> loadProducts(String prods) {
    	String[] split = prods.split(",");
    	
    	ArrayList<Product> products = new ArrayList<Product>();
    	
    	for(int i = 0; i < split.length; i++) {
    		switch (split[i]) {
			case "food":
				products.add(new Food());
				break;
			case "drink":
				products.add(new Drink());
				break;
			case "pen":
				products.add(new Pen());
				break;
			case "sheet":
				products.add(new Sheet());
				break;
			case "computer":
				products.add(new Computer());
				break;
			case "book":
				products.add(new Book());
				break;
			case "desk":
				products.add(new Desk());
				break;
			default:
				break;
			}
    	}
		return products;
    }
}