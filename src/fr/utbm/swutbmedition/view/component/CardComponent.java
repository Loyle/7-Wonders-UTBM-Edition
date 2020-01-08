package fr.utbm.swutbmedition.view.component;

import java.io.InputStream;

import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.model.product.Product;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CardComponent extends BorderPane {
	private Card card;
	
	public CardComponent(Card card) {
		this.card = card;
		
		this.setMinSize(220, 150);
		this.setBackground(new Background(new BackgroundFill(card.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
		
		VBox neededRessources = new VBox();
		neededRessources.setAlignment(Pos.TOP_LEFT);
		neededRessources.setMinWidth(35);
		//neededRessources.setPadding(new Insets(5));
		
		if(card.getCostMoney() > 0) {
			neededRessources.getChildren().add(new Text("O"));
		}
		else {
			for(Product product : card.getCostProduct()) {
				ImageView ico = product.getIcon();
				//ico.setFitHeight(30);
				ico.setFitWidth(35);
				ico.setPreserveRatio(true);
				neededRessources.getChildren().add(ico);
			}
		}
		
		this.setLeft(neededRessources);
		
		VBox centralPart = new VBox();
		centralPart.setAlignment(Pos.CENTER);
		centralPart.setSpacing(5);
		centralPart.setPadding(new Insets(5));
		
		HBox produced = new HBox();
		centralPart.getChildren().add(produced);
		
		Text text = new Text(card.getName());
		text.setFont(Font.font(20));
		centralPart.getChildren().add(text);
		
		this.setCenter(centralPart);
	}
}
