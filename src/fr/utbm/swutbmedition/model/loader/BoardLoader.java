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

public class BoardLoader {
	public static ArrayList<ArrayList<Board>> loadBoard() {
		ArrayList<ArrayList<Board>> boards = new ArrayList<ArrayList<Board>>();
		
		String csvFile = "./data/cards/BoardFile.csv";
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
                 ArrayList<Board> array = new ArrayList<Board>();
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
                 //Trouver moyen avec JSON;
                 ArrayList<Step> steps = new ArrayList<Step>();
                 array.add(new Board(split[1],split[2],product,steps));
                 boards.add(array);
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