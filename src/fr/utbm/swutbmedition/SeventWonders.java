package fr.utbm.swutbmedition;

import fr.utbm.swutbmedition.view.MainFrame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SeventWonders extends Application {
    public static void main(String [] args) {    	
    	Application.launch(SeventWonders.class,args);
    }

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("7 Wonders | UTBM Edition");
		primaryStage.setResizable(false);
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,800,600);
		scene.setFill(Color.BEIGE);
		primaryStage.setScene(scene);
		
		MainFrame frame = new MainFrame(primaryStage);
		frame.showMenuFrame();
		
		primaryStage.show();
	}

}