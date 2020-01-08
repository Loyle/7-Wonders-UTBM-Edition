package fr.utbm.swutbmedition.model.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fr.utbm.swutbmedition.model.board.Board;
import fr.utbm.swutbmedition.model.board.Step;
import fr.utbm.swutbmedition.model.product.Book;
import fr.utbm.swutbmedition.model.product.Computer;
import fr.utbm.swutbmedition.model.product.Desk;
import fr.utbm.swutbmedition.model.product.Drink;
import fr.utbm.swutbmedition.model.product.Food;
import fr.utbm.swutbmedition.model.product.Pen;
import fr.utbm.swutbmedition.model.product.Product;
import fr.utbm.swutbmedition.model.product.Sheet;



import fr.utbm.swutbmedition.model.board.Effect;

public class BoardLoader {
	@SuppressWarnings("unlikely-arg-type")
	public static ArrayList<ArrayList<Board>> loadBoard(){
		ArrayList<ArrayList<Board>> boards = new ArrayList<ArrayList<Board>>();
		int k=0;
		String csvFile = "./data/board/BoardFile.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        
        try {
			br = new BufferedReader(new FileReader(csvFile));
		
		
			br.readLine(); // On lit la première ligne qui est la ligne d'entête
			String[] split = null;
			while ((line = br.readLine()) != null) {
                
                // use comma as separator
                 split = line.split(cvsSplitBy);   
                 
                 Product product;
                 switch (split[3]) {
                 case "drink":
                	 product= new Drink();
                	 break;
                 case "sheet":
                	 product = new Sheet();
                	 break;
                 case "desk":
                	 product= new Desk();
                	 break;
                 case "PC":
                	 product = new Computer();
                	 break;
                 case "book":
                	 product = new Book();
                	 break;
                 case "food":
                	 product = new Food();
                	 break;
                 case "pen":
                	 product = new Pen();
                	 break;
                 default :
                	 product = null; 
                 }
                 
                 ArrayList<Product> step1Products = new ArrayList<Product>();
                 ArrayList<Product> step2Products = new ArrayList<Product>();
                 ArrayList<Product> step3Products = new ArrayList<Product>();
                 ArrayList<Product> step4Products = new ArrayList<Product>();
                 for(int i = 7; i <= 38; i++) {
                 	// On ajout les product
                 	if(!split[i].equals("-")) {
                 		int nb = Integer.valueOf(split[i]);
                 		for(int j = 0; j < nb; j++) {
                 			
  	                		Product step1Product = null;
  	                		Product step2Product = null;
  	                		Product step3Product = null;
  	                		Product step4Product = null;
 	                		switch (i) {
 							case 5:
 								step1Product = new Food();
 								break;
 							case 14:
 								step2Product = new Food();
 								break;
 							case 22:
 								step3Product = new Food();
 								break;
 							case 30: 							
 								step4Product = new Food();
 								break;
 							case 6:
 								step1Product = new Food();
 								break;
 							case 15:
 								step2Product = new Food();
 								break;
 							case 23:
 								step3Product = new Food();
 								break;
 							case 31:
 								step4Product = new Food();
 								break;
 							case 7:
 								step1Product = new Food();
 								break;
 							case 16:
 								step2Product = new Food();
 								break;
 							case 24:
 								step3Product = new Food();
 								break;
 							case 32:
 								step4Product = new Food();
 								break;
 							case 8:
 								step1Product = new Food();
 								break;
 							case 17:
 								step2Product = new Food();
 								break;
 							case 25:
 								step3Product = new Food();
 								break;
 							case 33:
 								step4Product = new Food();
 								break;
 							case 9:
 								step1Product = new Food();
 								break;
 							case 18:
 								step2Product = new Food();
 								break;
 							case 26:
 								step3Product = new Food();
 								break;
 							case 34:
 								step4Product = new Food();
 								break;
 							case 10:
 								step1Product = new Food();
 								break;
 							case 19:
 								step2Product = new Food();
 								break;
 							case 27:
 								step3Product = new Food();
 								break;
 							case 35:	
 								step4Product = new Food();
 								break;
 							case 11:
 								step1Product = new Food();
 								break;
 							case 20:
 								step2Product = new Food();
 								break;
 							case 28:
 								step3Product = new Food();
 								break;
 							case 36:
 								step4Product = new Food();
 								break;
 							default:
 								step1Product = null;
 								step2Product = null;
 								step3Product = null;
 								step4Product = null;
 								break;
 							}
 	                		step1Products.add(step1Product);
 	                		step2Products.add(step2Product);
 	                		step3Products.add(step3Product);
 	                		step4Products.add(step4Product);
                 		  }
                 		}
                 	}
                 ArrayList<fr.utbm.swutbmedition.model.board.Effect> effects = null;
                 Step step1 = new Step(step1Products, effects);
                 Step step2 = new Step(step2Products, effects);
                 Step step3 = new Step(step3Products, effects);
                 Step step4 = new Step(step4Products, effects);
                 
                 ArrayList<Step> steps = new ArrayList<Step>();
                 steps.add(step1);
                 steps.add(step2);
                 steps.add(step3);
                 steps.add(step4);
                 ArrayList<Board> array = new ArrayList<Board>();         
                 array.add(new Board(split[1],split[2],product,steps));
                 
                 if(k%2==1) {
                	 boards.add(array);
                	 
                 }
                 ++k;
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
		
		return boards;
	}
}
