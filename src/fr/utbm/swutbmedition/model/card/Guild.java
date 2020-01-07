package fr.utbm.swutbmedition.model.card;

import java.util.ArrayList;
import java.util.function.Predicate;

import javax.swing.ImageIcon;

import fr.utbm.swutbmedition.model.Game;
import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.board.Board;
import fr.utbm.swutbmedition.model.product.ManufacturedProduct;
import fr.utbm.swutbmedition.model.product.Product;
import fr.utbm.swutbmedition.model.product.RawMaterial;
import javafx.scene.paint.Color;

public class Guild extends Card {
	
	private String critere;

    public Guild(String name, int age, Color color, int costMoney, ArrayList<Product> costProduct, ImageIcon skin, int nbPlayerMin, String critere) {
    	super(name,age,color,costMoney,costProduct,skin,nbPlayerMin);
    	this.critere=critere;
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
		Predicate<Card> predicate = null;
		if (critere=="Scientific") {
			System.out.println("Critere -> SCIENTIFIC");
			predicate = (Card c) -> (c instanceof Scientific);
		}else if(critere=="Civil") {
			System.out.println("Critere -> CIVIL");
			predicate = (Card c) -> (c instanceof Civil);
		}else if (critere=="Military") {
			System.out.println("Critere -> MILITARY");
			predicate = (Card c) -> (c instanceof Military);			
		}else if(critere=="Commercial"){
			System.out.println("Critere -> COMMERCIAL");
			predicate = (Card c) -> (c instanceof Commercial);
		}else if(critere=="RawMaterial") {
			System.out.println("Critere -> RAWMAT");
			predicate = (Card c) -> (((ProductCard) c ).getProducts().get(0)instanceof RawMaterial);
		}else if(critere=="RawManGuild") {
			System.out.println("Critere -> TRIPLE CARD");
			predicate = (Card c) -> ((c instanceof Guild)||(((ProductCard) c ).getProducts().get(0)instanceof RawMaterial)||(((ProductCard) c ).getProducts().get(0)instanceof ManufacturedProduct));
		}else if(critere=="ManufacturedProduct") {
			System.out.println("Critere -> MANPRODUCT");
			predicate = (Card c) -> (((ProductCard) c ).getProducts().get(0)instanceof ManufacturedProduct);
		}
						
		int pointLeftPlayer=0, pointrightPlayer=0;
		
		int pos = g.getPlayers().indexOf(p);
		
		// Premier temps à droite (donc + 1 par rapport à numero du joueur)
		int toCheck = pos + 1;
		
		if(pos == g.getPlayers().size() - 1)
			toCheck = 0;
		
		for(Card card : g.getPlayers().get(toCheck).getUsedCard()) {
			if(predicate.test(card)) {
				System.out.println("+1");
				++pointrightPlayer;
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
		return new Guild(name, age, color, costMoney, costProduct, skin, nbPlayerMin,critere);
	}

}