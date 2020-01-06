package fr.utbm.swutbmedition.model.card;

import java.util.ArrayList;
import java.util.function.Predicate;

import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.Game;
import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.scene.paint.Color;

public class Guild extends Card {
	
	private String critere;

    public Guild(String name, int age, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin) {
    	super(name,age,color,costMoney,costProduct,skin,nbPlayerMin);
    }
    
    public void addPlayer() {
        // TODO implement here
    }

    protected void effect() {
	}

	@Override
	public void effect(Player p) {		
		p.setCreditsECTS(p.getCreditsECTS()); //+effet card		
	}
	
	public void effect(Game g ,Player p) {
		
		Predicate<Card> predicate;
		if (critere=="Scientific") {
			 predicate = (Card c) -> (c instanceof Scientific);
		}else if(critere=="Civil") {
			
		}else if (critere=="Military") {
			
		}else if(critere=="Commercial"){
			
		}else if(critere=="RawMaterial") {
			
		}else if(critere=="Merveille") {
			
		}else if(critere=="RawManGuild") {
			
		}
		
		
				
		int pointLeftPlayer=0, pointrightPlayer=0;
		
		int pos = g.getPlayers().indexOf(p);
		 
		
		// Premier temps à droite (donc + 1 par rapport à numero du joueur)
		int toCheck = pos + 1;
		
		if(pos == g.getPlayers().size() - 1)
			toCheck = 0;
		
		
		for(Card card : g.getPlayers().get(toCheck).getUsedCard()) {
			if(predicate.test(card)) {
				
			}
		}
		
		
		// Deuxième temps à gauche (donc + 1 par rapport à numero du joueur)
		toCheck = pos - 1;
		
		if(pos == 0)
			toCheck = g.getPlayers().size() - 1;
		
		
		p.setCreditsECTS(p.getCreditsECTS()+pointLeftPlayer+pointrightPlayer);
		
		
	}
	
	
	@Override
	public Card copy() {
		return new Guild(name, age, color, costMoney, costProduct, skin, nbPlayerMin);
	}

}