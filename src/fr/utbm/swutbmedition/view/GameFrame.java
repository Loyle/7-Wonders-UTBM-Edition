package fr.utbm.swutbmedition.view;

import java.util.HashMap;

import fr.utbm.swutbmedition.controller.GameController;
import fr.utbm.swutbmedition.model.Game;
import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.model.card.Card;
import fr.utbm.swutbmedition.view.component.BoardComponent;
import fr.utbm.swutbmedition.view.component.CardComponent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameFrame extends BorderPane {
    private Game game;
    private GameController gameController;
    private MainFrame mainFrame;
    
    private HashMap<Card, CardComponent> cardsButton;
    
    private BorderPane playerBoardLayout;
    private VBox scoreboard;
    private HBox gameStatus;
    private FlowPane playerHand;
    private FlowPane otherBoardsLayout;
    private HBox actionsLayout;
    private Card selectedCard;
    private Text textAction;
    
    private Button buildBtn;

    public GameFrame(MainFrame mainFrame) {
    	this.mainFrame = mainFrame;
    	
    	this.game = new Game();
    	this.gameController = new GameController(this, this.game);
    	this.selectedCard = null;
    	
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
    	
    	// A gauche on met le board du player
    	this.playerBoardLayout = new BorderPane();
    	this.playerBoardLayout.setStyle("-fx-border-style: solid outside;" + 
                "-fx-border-width: 1;" + 
                "-fx-border-color: black;" + 
                "-fx-background-color: #efefef;");
    	topLayout.getChildren().add(this.playerBoardLayout);
    	
    	// A droite c'est les cartes + action
    	VBox handAndActionLayout = new VBox();
    	topLayout.getChildren().add(handAndActionLayout);
    	handAndActionLayout.setPadding(new Insets(20,20,20,20));
    	handAndActionLayout.setStyle("-fx-border-style: solid outside;" + 
                "-fx-border-width: 1;" + 
                "-fx-border-color: black;" + 
                "-fx-background-color: #efefef;");
    	
    	this.playerBoardLayout.setMaxWidth(Double.MAX_VALUE);
    	handAndActionLayout.setMaxWidth(Double.MAX_VALUE);
    	HBox.setHgrow(this.playerBoardLayout, Priority.ALWAYS);
    	HBox.setHgrow(handAndActionLayout, Priority.ALWAYS);
    	
    	this.playerHand = new FlowPane(Orientation.HORIZONTAL,4,2);
    	this.playerHand.setAlignment(Pos.TOP_CENTER);
    	this.playerHand.setPadding(new Insets(10));
    	
    	
    	handAndActionLayout.getChildren().add(this.playerHand);
    	
    	this.buildBtn = new Button("Construire");
    	this.buildBtn.setCursor(Cursor.HAND);
    	this.buildBtn.setMaxWidth(Double.MAX_VALUE);
    	this.buildBtn.setPrefHeight(80);
    	
    	Button btnSell = new Button("Vendre pour 3€");
    	btnSell.setMaxWidth(Double.MAX_VALUE);
    	btnSell.setCursor(Cursor.HAND);
    	btnSell.setPrefHeight(80);

    	Button btnBuildWonder = new Button("Constuire la merveille");
    	btnBuildWonder.setCursor(Cursor.HAND);
    	btnBuildWonder.setMaxWidth(Double.MAX_VALUE);
    	btnBuildWonder.setPrefHeight(80);
    	
    	textAction = new Text("");
    	
    	btnBuildWonder.setOnAction(e ->{
    		if (selectedCard != null) {
    			gameController.buildWonder(game.getCurrentPlayer(), selectedCard);
    			//selectedCard = null;
    		}
    		else {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText("Veuillez sélectionner une carte");
    			alert.showAndWait();
    		}
    	});
    	
    	this.buildBtn.setOnAction(e -> {
    		if (selectedCard != null) {
    			if(gameController.useCard(game.getCurrentPlayer(), selectedCard))
    				selectedCard = null;
    		}
    		else {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText("Veuillez sélectionner une carte");
    			alert.showAndWait();
    		}
    	});
    	
    	btnSell.setOnAction(e -> {
    		if (selectedCard != null) {
    			gameController.sellCard(game.getCurrentPlayer(), selectedCard);
    			selectedCard = null;
    		}
    		else {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText("Veuillez sélectionner une carte");
    			alert.showAndWait();
    		}
    	});
    	
    	btnBuildWonder.setOnAction(e -> {
    		if (selectedCard != null) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText(gameController.buildWonder(game.getCurrentPlayer(), selectedCard));
    			alert.showAndWait();
    			selectedCard = null;
    		}
    		else {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText("Veuillez sélectionner une carte");
    			alert.showAndWait();
    		}
    	});
    	
    	this.actionsLayout = new HBox();
    	this.actionsLayout.getChildren().addAll(this.buildBtn,btnSell,btnBuildWonder);
    	this.actionsLayout.setAlignment(Pos.BOTTOM_CENTER);
    	this.actionsLayout.setSpacing(10);
    	HBox.setHgrow(this.buildBtn, Priority.ALWAYS);
    	HBox.setHgrow(btnSell, Priority.ALWAYS);
    	HBox.setHgrow(btnBuildWonder, Priority.ALWAYS);
    	
    	VBox blockAction = new VBox(10);
    	blockAction.getChildren().addAll(actionsLayout,textAction);
    	
    	handAndActionLayout.getChildren().add(blockAction);
    	
    	this.playerHand.setMaxHeight(Double.MAX_VALUE);
    	this.actionsLayout.setMaxHeight(Double.MAX_VALUE);
    	VBox.setVgrow(this.playerHand, Priority.ALWAYS);
    	VBox.setVgrow(this.actionsLayout, Priority.ALWAYS);
    	
    	// Layout de la moitié basse
    	HBox bottomLayout = new HBox();
    	mainLayout.getChildren().add(bottomLayout);
    	
    	// Grille de tous les boards
    	this.otherBoardsLayout = new FlowPane(Orientation.HORIZONTAL,4,2);
    	this.otherBoardsLayout.setAlignment(Pos.CENTER);
    	this.otherBoardsLayout.setMaxWidth(Double.MAX_VALUE);
    	this.otherBoardsLayout.setStyle("-fx-border-style: solid outside;" + 
                "-fx-border-width: 1;" + 
                "-fx-border-color: black;" + 
                "-fx-background-color: #efefef;");
    	bottomLayout.getChildren().add(this.otherBoardsLayout);
    	
    	// Scoreboard
    	this.scoreboard = new VBox();
    	this.scoreboard.setPadding(new Insets(40,40,40,40));
    	this.scoreboard.setAlignment(Pos.CENTER);
    	this.scoreboard.setSpacing(20);
    	this.scoreboard.setMaxWidth(300);
    	this.scoreboard.setStyle("-fx-border-style: solid outside;" + 
                "-fx-border-width: 1;" + 
                "-fx-border-color: black;" + 
                "-fx-background-color: #efefef;");
    	bottomLayout.getChildren().add(this.scoreboard);
    	
    	HBox.setHgrow(this.otherBoardsLayout, Priority.ALWAYS);
    	HBox.setHgrow(this.scoreboard, Priority.ALWAYS);
    	
    	topLayout.setMaxHeight(Double.MAX_VALUE);
    	bottomLayout.setMaxHeight(Double.MAX_VALUE);
    	VBox.setVgrow(topLayout, Priority.ALWAYS);
    	VBox.setVgrow(bottomLayout, Priority.ALWAYS);
    	
    	this.gameStatus = new HBox();
    	this.gameStatus.getChildren().add(new Text("Tour n°" + this.game.getRound() + " | Age n°" + this.game.getAge()));
    	this.gameStatus.setAlignment(Pos.CENTER);
    	this.gameStatus.setStyle("-fx-background-color: white; -fx-border-bottom-style: solid outside;");
    	this.gameStatus.setPadding(new Insets(20,20,20,20));
    	this.setTop(this.gameStatus);
    	
    	this.setCenter(mainLayout);
    }
    
    public void refreshScoreboard() {
    	this.scoreboard.getChildren().clear();
    	Text title = new Text("Conflits");
    	title.setFont(Font.font(25));
    	this.scoreboard.getChildren().add(title);
    	for(Player p : this.game.getPlayers()) {
    		p.countScore(game);
    		Text text = new Text(p.getName() + " : " + p.countConflicts());
    		text.setFont(Font.font(20));
    		this.scoreboard.getChildren().add(text);   		
    	}
    }
    public void refreshGameStatus() {
    	this.gameStatus.getChildren().clear();
    	Text text = new Text("C'est à "+ this.game.getCurrentPlayer().getName() +" de jouer           Tour n°" + (int) (this.game.getRound() + 1) + "            Age n°" + this.game.getAge());
    	text.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,30));
    	this.gameStatus.getChildren().add(text);
    }


	public void displayPlayerHand(Player currentPlayer) {
		this.playerHand.getChildren().clear();
		
		this.cardsButton = new HashMap<Card, CardComponent>();
		
		for(Card card : currentPlayer.getHandCards()) {
			CardComponent btn = new CardComponent(card, false);
			this.cardsButton.put(card, btn);
			btn.setMinSize(220, 150);
			btn.setMaxWidth(Double.MAX_VALUE);
			btn.setBackground(new Background(new BackgroundFill(card.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
			btn.setCursor(Cursor.HAND);
	    	btn.setOnMouseClicked(e -> {
	    		if(this.selectedCard != null) {
	    			this.cardsButton.get(this.selectedCard).setStyle("");
	    		}
	    		this.selectedCard = card;
	    		
	    		btn.setStyle("-fx-border-style: solid outside;" + 
                        "-fx-border-width: 3;" +
                        "-fx-border-radius: 1;" + 
                        "-fx-border-color: black;");
	    		
	    		if(this.gameController.canUseCard(currentPlayer, card)) {
	    			// Good elle peut être joué
	    			this.buildBtn.setBackground(new Background(new BackgroundFill(Color.web("#baffba"), CornerRadii.EMPTY, Insets.EMPTY)));
	    		}
	    		else {
	    			// Manque quelque chose
	    			this.buildBtn.setBackground(new Background(new BackgroundFill(Color.web("#ffd3cd"), CornerRadii.EMPTY, Insets.EMPTY)));
	    		}
	    	});
	    	//HBox.setHgrow(btn, Priority.ALWAYS);
			this.playerHand.getChildren().add(btn);
		}
	}
	
	public void displayPlayerBoard(Player player) {
			this.playerBoardLayout.getChildren().clear();
			this.playerBoardLayout.setCenter(new BoardComponent(player,true));			
	}
	
	public void refreshAllBoards() {
		this.otherBoardsLayout.getChildren().clear();
		for(Player player : this.game.getPlayers()) {
			BoardComponent board = new BoardComponent(player, false);
			this.otherBoardsLayout.getChildren().add(board);
			
			board.setCursor(Cursor.HAND);
			board.setOnMouseClicked(e -> {
				this.displayPlayerBoard(player);
			});
		}
	}
	
	public MainFrame getMainFrame() {
		return this.mainFrame;
	}
	public void finish() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Fin de partie");
		String score = new String("");
		for(Player p : this.game.getPlayers()) {
			p.countScore(this.game);
			score += p.getName() + " : " + p.getCreditsECTS() + "\n";
		}
		alert.setContentText(score);
		alert.showAndWait();
	}
}