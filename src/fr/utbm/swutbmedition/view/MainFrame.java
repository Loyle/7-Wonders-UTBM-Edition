package fr.utbm.swutbmedition.view;

import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFrame {
	private MenuFrame menuFrame;
	private GameFrame gameFrame;
	private RulesFrame rulesFrames;
	private Stage primaryStage;
	
	public MainFrame(Stage primaryStage)  {
		this.primaryStage = primaryStage;
				
		this.menuFrame = new MenuFrame(this);
		this.gameFrame = new GameFrame(this);
		this.rulesFrames = new RulesFrame(this);
		
		Scene scene = new Scene(this.menuFrame);
		this.getPrimaryStage().setScene(scene);
		
		this.getPrimaryStage().setMinHeight(600);
		this.getPrimaryStage().setMinWidth(1000);
		
		this.getPrimaryStage().setMaximized(true);
		this.getPrimaryStage().setResizable(true);
	}
	
	public void showMenuFrame() {
		this.menuFrame.showMenu();
		this.getPrimaryStage().getScene().setRoot(this.menuFrame);
	}
	
	public void showGameFrame() {
		this.gameFrame.getGameController().initGame();
		this.gameFrame.getGameController().start();
		this.getPrimaryStage().getScene().setRoot(this.gameFrame);
	}
	
	public void showRulesFrame() {
		
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
}
