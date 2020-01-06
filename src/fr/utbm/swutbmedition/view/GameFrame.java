package fr.utbm.swutbmedition.view;

import fr.utbm.swutbmedition.controller.GameController;
import fr.utbm.swutbmedition.model.Game;
import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.card.Card;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameFrame extends BorderPane {
    private Game game;
    private GameController gameController;
    private MainFrame mainFrame;
    
    private GridPane playerBoardLayout;
    private VBox scoreboard;
    private HBox gameStatus;
    private HBox playerHand;
    private GridPane otherBoardsLayout;
    private HBox actionsLayout;

    public GameFrame(MainFrame mainFrame) {
    	this.mainFrame = mainFrame;
    	
    	this.game = new Game();
    	this.gameController = new GameController(this, this.game);
    	
    	this.initFrame();
    }
      
    public GameController getGameController() {
    	return this.gameController;
    }
    
    public void initFrame() {
    	VBox mainLayout = new VBox();
    	
    	// Layout de la moitié haut
    	HBox topLayout = new HBox();
    	mainLayout.getChildren().add(topLayout);
    	
    	// A gauche on met le boad du player
    	this.playerBoardLayout = new GridPane();
    	this.playerBoardLayout.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    	topLayout.getChildren().add(this.playerBoardLayout);
    	
    	// A droite c'est les cartes + action
    	VBox handAndActionLayout = new VBox();
    	topLayout.getChildren().add(handAndActionLayout);
    	
    	this.playerBoardLayout.setMaxWidth(Double.MAX_VALUE);
    	handAndActionLayout.setMaxWidth(Double.MAX_VALUE);
    	HBox.setHgrow(this.playerBoardLayout, Priority.ALWAYS);
    	HBox.setHgrow(handAndActionLayout, Priority.ALWAYS);
    	
    	this.playerHand = new HBox();
    	this.playerHand.setSpacing(1);
    	this.playerHand.setAlignment(Pos.CENTER);
    	
    	this.playerHand.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	handAndActionLayout.getChildren().add(this.playerHand);
    	
    	this.actionsLayout = new HBox();
    	this.actionsLayout.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    	handAndActionLayout.getChildren().add(this.actionsLayout);
    	
    	this.playerHand.setMaxHeight(Double.MAX_VALUE);
    	this.actionsLayout.setMaxHeight(Double.MAX_VALUE);
    	VBox.setVgrow(this.playerHand, Priority.ALWAYS);
    	VBox.setVgrow(this.actionsLayout, Priority.ALWAYS);
    	
    	// Layout de la moitié basse
    	HBox bottomLayout = new HBox();
    	mainLayout.getChildren().add(bottomLayout);
    	
    	// Grille de tous les boards
    	this.otherBoardsLayout = new GridPane();
    	this.otherBoardsLayout.setMaxWidth(Double.MAX_VALUE);
    	this.otherBoardsLayout.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
    	bottomLayout.getChildren().add(this.otherBoardsLayout);
    	
    	// Scoreboard
    	this.scoreboard = new VBox();
    	this.scoreboard.setMaxWidth(300);
    	this.scoreboard.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
    	bottomLayout.getChildren().add(this.scoreboard);
    	
    	HBox.setHgrow(this.otherBoardsLayout, Priority.ALWAYS);
    	HBox.setHgrow(this.scoreboard, Priority.ALWAYS);
    	
    	
    	topLayout.setMaxHeight(Double.MAX_VALUE);
    	bottomLayout.setMaxHeight(Double.MAX_VALUE);
    	VBox.setVgrow(topLayout, Priority.ALWAYS);
    	VBox.setVgrow(bottomLayout, Priority.ALWAYS);
    	
    	this.gameStatus = new HBox();
    	//this.gameStatus.getChildren().add(new Text("Tour n°" + this.game.getRound() + " | Age n°" + this.game.getAge()));
    	//this.gameStatus.setAlignment(Pos.CENTER);
    	//this.setTop(this.gameStatus);
    	
    	
    	/*HBox layout = new HBox();
    	Button btn = new Button("Click ici ca te fais jouer");
    	btn.setOnMouseClicked(new EventHandler<MouseEvent >() {
    		public void handle(MouseEvent e) {
    			gameController.next();
    		}
		});
    	layout.getChildren().add(btn);*/
    	
    	this.setCenter(mainLayout);
    }
    
    public void refreshScoreboard() {
    	this.scoreboard.getChildren().clear();
    	for(Player p : this.game.getPlayers()) {
    		p.countScore(game);
    		this.scoreboard.getChildren().add(new Text(p.getName() + " : " + p.getCreditsECTS()));   		
    	}
    }
    public void refreshGameStatus() {
    	this.gameStatus.getChildren().clear();
    	this.gameStatus.getChildren().add(new Text("C'est à " + this.game.getCurrentPlayer().getName() + " de jouer | Tour n°" + (int) (this.game.getRound() + 1) + " | Age n°" + this.game.getAge()));
    }


	public void displayBoard(Player currentPlayer) {
		this.playerHand.getChildren().clear();
		for(Card card : currentPlayer.getHandCards()) {
			Button btn = new Button(card.getName());
			btn.setMinHeight(100);
			btn.setMaxWidth(Double.MAX_VALUE);
			btn.setBackground(new Background(new BackgroundFill(card.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
	    	btn.setOnMouseClicked(new EventHandler<MouseEvent >() {
	    		public void handle(MouseEvent e) {
	    			gameController.useCard(currentPlayer, card);
	    		}
			});
	    	HBox.setHgrow(btn, Priority.ALWAYS);
	    	this.playerHand.getChildren().add(btn);
		}
	}

}