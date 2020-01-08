package fr.utbm.swutbmedition.view.component;

import fr.utbm.swutbmedition.model.Player;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BoardComponent extends BorderPane{
	private Player player;
	
	public BoardComponent(Player player, boolean isBig) {
		this.player = player;
		
		int size = 35;
		if(isBig)
			size = 70;
		else
			this.setPrefSize(220,150);
		
		
		this.setStyle("-fx-background-color: #eae7d4;");
		
		
		
		HBox info = new HBox();
		info.setAlignment(Pos.TOP_CENTER);
		info.setSpacing(50);
		info.setPadding(new Insets(10));
		
		// Premier ressource a afficher, celle du board
		//ImageView boardProduct = new ImageView(player.getBoard().getProduct().getIcon());
		//boardProduct.setFitWidth(35);
		//boardProduct.setPreserveRatio(true);
		//info.getChildren().add(boardProduct);
		
		Text pseudo = new Text(player.getName());
		pseudo.setFont(Font.font((isBig) ? 40 : 15));
			
		info.getChildren().add(pseudo);
			
		if(isBig) {
			HBox money = new HBox();
			money.setSpacing(10);
			money.setAlignment(Pos.CENTER);
			
			Text moneyValue = new Text("" + player.getMoney());
			moneyValue.setFont(Font.font(35));
			money.getChildren().add(moneyValue);
			
			ImageView ico = new ImageView("file:./data/images/pieceRond.png");
			ico.setFitWidth(30);
			ico.setPreserveRatio(true);
			money.getChildren().add(ico);
			
			info.getChildren().add(money);
			
		}
		this.setTop(info);
		
		FlowPane playerRessources = new FlowPane(Orientation.HORIZONTAL,4,2);
		playerRessources.setAlignment(Pos.TOP_LEFT);
		
		if(isBig) {
			// Product des cards ensuite
			for(Card card : player.getUsedCard()) {
				playerRessources.getChildren().add(new CardComponent(card, true));
			}			
		}
		else {
			for(Product product : player.getAllProducts()) {
				ImageView ico = new ImageView(product.getIcon());
				ico.setFitWidth(30);
				ico.setPreserveRatio(true);
				playerRessources.getChildren().add(ico);
			}
		}
		
		this.setCenter(playerRessources);
		
	}
}
