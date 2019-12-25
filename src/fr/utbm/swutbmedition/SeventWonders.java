package fr.utbm.swutbmedition;

import fr.utbm.swutbmedition.view.MenuFrame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
		
		Group root = new Group();
		Scene scene = new Scene(root,800,600);
		scene.setFill(Color.BEIGE);
		primaryStage.setScene(scene);
		
		MenuFrame menu = new MenuFrame();
		menu.setTranslateX(250);
		menu.setTranslateY(200);
		root.getChildren().add(menu);
		
		primaryStage.show();
	}

}