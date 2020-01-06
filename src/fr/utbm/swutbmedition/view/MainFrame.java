package fr.utbm.swutbmedition.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFrame {
	private MenuFrame menuFrame;
	private GameFrame gameFrame;
	private RulesFrame rulesFrames;
	private Stage primaryStage;
	
	public MainFrame(Stage primaryStage) {
		this.primaryStage = primaryStage;
				
		this.menuFrame = new MenuFrame(this);
		this.gameFrame = new GameFrame(this);
		this.rulesFrames = new RulesFrame(this);
		
		Scene scene = new Scene(this.menuFrame);
		this.primaryStage.setScene(scene);
		
		this.primaryStage.setMinHeight(600);
		this.primaryStage.setMinWidth(1000);
		
		this.primaryStage.setMaximized(true);
		this.primaryStage.setResizable(true);
	}
	
	public void showMenuFrame() {
		this.menuFrame.showMenu();
		this.primaryStage.getScene().setRoot(this.menuFrame);
	}
	
	public void showGameFrame() {
		this.gameFrame.getGameController().initGame();
		this.gameFrame.getGameController().start();
		this.primaryStage.getScene().setRoot(this.gameFrame);
	}
	
	public void showRulesFrame() {
		
	}
}
