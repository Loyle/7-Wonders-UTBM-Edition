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
		
		int pointLeftPlayer=0, pointRightPlayer=0;
		
		int pos = g.getPlayers().indexOf(p);
		
		// Premier temps à droite (donc + 1 par rapport à numero du joueur)
		int toCheck = pos + 1;
		
		if(pos == g.getPlayers().size() - 1)
			toCheck = 0;
		
		for(Card card : g.getPlayers().get(toCheck).getUsedCard()) {
			if (critere.equalsIgnoreCase("Scientific")) {
				if(card instanceof Scientific) {
					++pointRightPlayer;
				}
			}else if(critere.equalsIgnoreCase("Civil")) {
				if(card instanceof Civil) {
					++pointRightPlayer;
				}
			}else if (critere.equalsIgnoreCase("Military")) {
				if(card instanceof Military) {
					++pointRightPlayer;
				}
			}else if(critere.equalsIgnoreCase("Commercial")){
				if(card instanceof Commercial) {
					++pointRightPlayer;
				}
			}else if(critere.equalsIgnoreCase("RawMaterial")) {
				if(((ProductCard)card).getProducts().get(0)instanceof RawMaterial) {
					++pointRightPlayer;
				}
			}else if(critere.equalsIgnoreCase("RawManGuild")) {
				if(((card instanceof Guild)||(((ProductCard) card ).getProducts().get(0)instanceof RawMaterial)||(((ProductCard) card ).getProducts().get(0)instanceof ManufacturedProduct))) {
					++pointRightPlayer;
				}
				
			}else if(critere.equalsIgnoreCase("ManufacturedProduct")) {
				if(((ProductCard)card).getProducts().get(0)instanceof ManufacturedProduct) {
					pointRightPlayer +=2;
				}
			}
			
		}
		
		if (critere.equalsIgnoreCase("Merveille")) {
			pointRightPlayer+= g.getPlayers().get(toCheck).getBoard().getLevel();

		}else if(critere.equalsIgnoreCase("Conflict")) {
			for(int i : g.getPlayers().get(toCheck).getConflicts()) {
				if(i==(-1)) {
					++pointRightPlayer;
				}
			}
		}
		
		
		// Deuxième temps à gauche (donc + 1 par rapport à numero du joueur)
		toCheck = pos - 1;
		
		if(pos == 0)
			toCheck = g.getPlayers().size() - 1;
		for(Card card : g.getPlayers().get(toCheck).getUsedCard()) {
			if (critere.equalsIgnoreCase("Scientific")) {
				if(card instanceof Scientific) {
					++pointLeftPlayer;
				}
			}else if(critere.equalsIgnoreCase("Civil")) {
				if(card instanceof Civil) {
					++pointLeftPlayer;
				}
			}else if (critere.equalsIgnoreCase("Military")) {
				if(card instanceof Military) {
					++pointLeftPlayer;
				}
			}else if(critere.equalsIgnoreCase("Commercial")){
				if(card instanceof Commercial) {
					++pointLeftPlayer;
				}
			}else if(critere.equalsIgnoreCase("RawMaterial")) {
				if(((ProductCard)card).getProducts().get(0)instanceof RawMaterial) {
					++pointLeftPlayer;
				}
			}else if(critere.equalsIgnoreCase("RawManGuild")) {
				if(((card instanceof Guild)||(((ProductCard) card ).getProducts().get(0)instanceof RawMaterial)||(((ProductCard) card ).getProducts().get(0)instanceof ManufacturedProduct))) {
					++pointLeftPlayer;
				}
			}else if(critere.equalsIgnoreCase("ManufacturedProduct")) {
				if(((ProductCard)card).getProducts().get(0)instanceof ManufacturedProduct) {
					pointLeftPlayer +=2;
				}
			}
		}
		
		if (critere.equalsIgnoreCase("Merveille")) {
			pointLeftPlayer+= g.getPlayers().get(toCheck).getBoard().getLevel();
		}else if(critere.equalsIgnoreCase("Conflict")) {
			for(int i : g.getPlayers().get(toCheck).getConflicts()) {
				if(i==(-1)) {
					++pointLeftPlayer;
				}
			}
		}
		int pointOwnWonders =0;
	if (critere.equalsIgnoreCase("Merveille")) {
			
			pointOwnWonders= p.getBoard().getLevel();
		}
		p.setCreditsECTS(p.getCreditsECTS()+pointLeftPlayer+pointRightPlayer+pointOwnWonders);
		
	}
	
	
	@Override
	public Card copy() {
		return new Guild(name, age, color, costMoney, costProduct, skin, nbPlayerMin,critere);
	}

}