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
			System.out.println(card.getName());
			if (critere.equalsIgnoreCase("Scientific")) {
				System.out.println("Critere -> SCIENTIFIC");
				if(card instanceof Scientific) {
					System.out.println("+1");
					++pointRightPlayer;
				}
			}else if(critere.equalsIgnoreCase("Civil")) {
				System.out.println("Critere -> CIVIL");
				if(card instanceof Civil) {
					System.out.println("+1");
					++pointRightPlayer;
				}
			}else if (critere.equalsIgnoreCase("Military")) {
				System.out.println("Critere -> MILITARY");
				if(card instanceof Military) {
					System.out.println("+1");
					++pointRightPlayer;
				}
			}else if(critere.equalsIgnoreCase("Commercial")){
				System.out.println("Critere -> COMMERCIAL");
				if(card instanceof Commercial) {
					System.out.println("+1");
					++pointRightPlayer;
				}
			}else if(critere.equalsIgnoreCase("RawMaterial")) {
				System.out.println("Critere -> RAWMAT");
				if(((ProductCard)card).getProducts().get(0)instanceof RawMaterial) {
					System.out.println("+1");
					++pointRightPlayer;
				}
			}else if(critere.equalsIgnoreCase("RawManGuild")) {
				System.out.println("Critere -> TRIPLE CARD");
				if(((card instanceof Guild)||(((ProductCard) card ).getProducts().get(0)instanceof RawMaterial)||(((ProductCard) card ).getProducts().get(0)instanceof ManufacturedProduct))) {
					System.out.println("+1");
					++pointRightPlayer;
				}
				
			}else if(critere.equalsIgnoreCase("ManufacturedProduct")) {
				System.out.println("Critere -> MANPRODUCT");
				if(((ProductCard)card).getProducts().get(0)instanceof ManufacturedProduct) {
					System.out.println("+1");
					pointRightPlayer +=2;
				}
			}
			
		}
		
		if (critere.equalsIgnoreCase("Merveille")) {
			
			System.out.println("Critere -> Merveille");
			System.out.println("+1");
			pointRightPlayer+= g.getPlayers().get(toCheck).getBoard().getLevel();
			
		}else if(critere.equalsIgnoreCase("Conflict")) {
			System.out.println("Critere -> Conflict");
			System.out.println("+1");
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
			System.out.println(card.getName());
			if (critere.equalsIgnoreCase("Scientific")) {
				System.out.println("Critere -> SCIENTIFIC");
				if(card instanceof Scientific) {
					System.out.println("+1");
					++pointLeftPlayer;
				}
			}else if(critere.equalsIgnoreCase("Civil")) {
				System.out.println("Critere -> CIVIL");
				if(card instanceof Civil) {
					System.out.println("+1");
					++pointLeftPlayer;
				}
			}else if (critere.equalsIgnoreCase("Military")) {
				System.out.println("Critere -> MILITARY");
				if(card instanceof Military) {
					System.out.println("+1");
					++pointLeftPlayer;
				}
			}else if(critere.equalsIgnoreCase("Commercial")){
				System.out.println("Critere -> COMMERCIAL");
				if(card instanceof Commercial) {
					System.out.println("+1");
					++pointLeftPlayer;
				}
			}else if(critere.equalsIgnoreCase("RawMaterial")) {
				System.out.println("Critere -> RAWMAT");
				if(((ProductCard)card).getProducts().get(0)instanceof RawMaterial) {
					System.out.println("+1");
					++pointLeftPlayer;
				}
			}else if(critere.equalsIgnoreCase("RawManGuild")) {
				System.out.println("Critere -> TRIPLE CARD");
				if(((card instanceof Guild)||(((ProductCard) card ).getProducts().get(0)instanceof RawMaterial)||(((ProductCard) card ).getProducts().get(0)instanceof ManufacturedProduct))) {
					System.out.println("+1");
					++pointLeftPlayer;
				}
				
			}else if(critere.equalsIgnoreCase("ManufacturedProduct")) {
				System.out.println("Critere -> MANPRODUCT");
				if(((ProductCard)card).getProducts().get(0)instanceof ManufacturedProduct) {
					System.out.println("+1");
					pointLeftPlayer +=2;
				}
			}
			
		}
		
		if (critere.equalsIgnoreCase("Merveille")) {
			
			System.out.println("Critere -> Merveille");
			System.out.println("+1");
			pointLeftPlayer+= g.getPlayers().get(toCheck).getBoard().getLevel();
			
		}else if(critere.equalsIgnoreCase("Conflict")) {
			System.out.println("Critere -> Conflict");
			System.out.println("+1");
			for(int i : g.getPlayers().get(toCheck).getConflicts()) {
				if(i==(-1)) {
					++pointLeftPlayer;
				}
			}
		}
		
	if (critere.equalsIgnoreCase("Merveille")) {
			int pointOwnWonders =0;
			System.out.println("Critere -> Merveille");
			System.out.println("+1");
			pointOwnWonders= p.getBoard().getLevel();
			
		}
		p.setCreditsECTS(p.getCreditsECTS()+pointLeftPlayer+pointRightPlayer);
		
	}
	
	
	@Override
	public Card copy() {
		return new Guild(name, age, color, costMoney, costProduct, skin, nbPlayerMin,critere);
	}

}