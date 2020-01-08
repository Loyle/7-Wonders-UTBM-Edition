package fr.utbm.swutbmedition.view.component;


import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.model.card.Civil;
import fr.utbm.swutbmedition.model.card.Military;
import fr.utbm.swutbmedition.model.card.ProductCard;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CardComponent extends BorderPane {
	private Card card;
	
	public CardComponent(Card card, boolean smallCard) {
		this.card = card;		
		
		this.setMinWidth((smallCard) ? 150 : 220);
		this.setPrefHeight((smallCard) ? 70 : 152);
		this.setBackground(new Background(new BackgroundFill(card.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
		
		
		if(!smallCard) {
			FlowPane neededRessources = new FlowPane(Orientation.VERTICAL,2,4);
			neededRessources.setAlignment(Pos.TOP_LEFT);
			neededRessources.setMinWidth(35);
			
			if(card.getCostMoney() > 0) {
				for(int i = 0; i < card.getCostMoney(); i++) {
					ImageView ico = new ImageView("file:./data/images/pieceRond.png");
					ico.setFitWidth(35);
					ico.setPreserveRatio(true);
					neededRessources.getChildren().add(ico);				
				}
			}
			else {
				for(Product product : card.getCostProduct()) {
					ImageView ico = new ImageView(product.getIcon());
					ico.setFitWidth(35);
					ico.setPreserveRatio(true);
					neededRessources.getChildren().add(ico);
				}
			}
			
			this.setLeft(neededRessources);
		}
		
		VBox centralPart = new VBox();
		centralPart.setAlignment(Pos.CENTER);
		centralPart.setSpacing(5);
		
		HBox produced = new HBox();
		centralPart.getChildren().add(produced);
		produced.setMinHeight(42);
		produced.setAlignment(Pos.CENTER);
		
		if (card instanceof ProductCard) {
			for(Product product : ((ProductCard) card).getProducts()) {
				ImageView ico = new ImageView(product.getIcon());
				ico.setFitWidth((smallCard) ? 30 : 35);
				ico.setPreserveRatio(true);
				produced.getChildren().add(ico);
			}
		}
		else if(card instanceof Civil) {
			Text text = new Text(((Civil) card).getCreditsECTS() + " ECTS");
			text.setFont(Font.font(20));
			produced.getChildren().add(text);
		}
		else if(card instanceof Military) {
			Text text = new Text(((Military)card).getFx() + " Fx");
			text.setFont(Font.font(20));
			produced.getChildren().add(text);
		}
		else {
			produced.getChildren().add(new Text(""));			
		}
		
		
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: white;");
		pane.setPrefSize((smallCard) ? 150 : 220, (smallCard) ? 50 : 100);
		
		Text text = new Text(card.getName());
		text.setFont(Font.font((smallCard) ? 14 : 20));
		
		pane.setCenter(text);
		
		centralPart.getChildren().add(pane);
		centralPart.setAlignment(Pos.BOTTOM_CENTER);
		
		this.setCenter(centralPart);
	}
}
