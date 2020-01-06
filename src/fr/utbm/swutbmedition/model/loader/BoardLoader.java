package fr.utbm.swutbmedition.model.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fr.utbm.swutbmedition.model.board.Board;

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
			while ((line = br.readLine()) != null) {
                
                // use comma as separator
                String[] split = line.split(cvsSplitBy);   
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