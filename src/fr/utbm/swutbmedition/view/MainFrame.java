package fr.utbm.swutbmedition.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFrame {
	private MenuFrame menuFrame;
	private GameFrame gameFrame;
	private RulesFrame rulesFrames;
	private Stage primaryStage;
	private Scene scene;
	
	public MainFrame(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		this.menuFrame = new MenuFrame(this, new BorderPane());
		this.gameFrame = new GameFrame(this, new BorderPane());
		this.rulesFrames = new RulesFrame(this);
	}
	
	public void showMenuFrame() {
		this.primaryStage.setScene(this.menuFrame);
		this.menuFrame.showMenu();
	}
	
	public void showGameFrame() {
		this.primaryStage.setScene(this.gameFrame);
	}
	
	public void showRulesFrame() {
		
	}
}
