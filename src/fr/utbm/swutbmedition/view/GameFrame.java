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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameFrame extends Scene {
    private Game game;
    private GameController gameController;
    private MainFrame mainFrame;
    private BorderPane root;
    
    private VBox scoreboard;
    private HBox gameStatus;
    private HBox cardsLayout;

    public GameFrame(MainFrame mainFrame, BorderPane root) {
    	super(root,800,600);
    	this.root = root;
    	this.mainFrame = mainFrame;
    	
    	this.game = new Game();
    	this.gameController = new GameController(this, this.game);
    	
    	this.initFrame();
    }
    
    public void start() {
    	this.gameController.initGame();
    	this.gameController.start();
    }
    
    public void initFrame() {
    	this.gameStatus = new HBox();
    	//this.gameStatus.getChildren().add(new Text("Tour n°" + this.game.getRound() + " | Age n°" + this.game.getAge()));
    	this.gameStatus.setAlignment(Pos.CENTER);
    	this.root.setTop(this.gameStatus);
    	
    	
    	this.scoreboard = new VBox();
    	for(Player p : this.game.getPlayers()) {
    		this.scoreboard.getChildren().add(new Text(p.getName() + " : " + p.getCreditsECTS()));    		
    	}
    	this.root.setRight(this.scoreboard);
    	
    	HBox layout = new HBox();
    	Button btn = new Button("Click ici ca te fais jouer");
    	btn.setOnMouseClicked(new EventHandler<MouseEvent >() {
    		public void handle(MouseEvent e) {
    			gameController.next();
    		}
		});
    	layout.getChildren().add(btn);
    	
    	this.root.setCenter(layout);
    	
    	
    	this.cardsLayout = new HBox();
    	this.cardsLayout.setSpacing(1);
    	this.cardsLayout.setAlignment(Pos.CENTER);
    	this.root.setBottom(this.cardsLayout);
    }
    
    public void refreshScoreboard() {
    	this.scoreboard.getChildren().clear();
    	for(Player p : this.game.getPlayers()) {
    		p.countScore();
    		this.scoreboard.getChildren().add(new Text(p.getName() + " : " + p.getCreditsECTS()));   		
    	}
    }
    public void refreshGameStatus() {
    	this.gameStatus.getChildren().clear();
    	this.gameStatus.getChildren().add(new Text("C'est à " + this.game.getCurrentPlayer().getName() + " de jouer | Tour n°" + (int) (this.game.getRound() + 1) + " | Age n°" + this.game.getAge()));
    }


	public void displayBoard(Player currentPlayer) {
		this.cardsLayout.getChildren().clear();
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
	    	this.cardsLayout.getChildren().add(btn);
		}
	}

}