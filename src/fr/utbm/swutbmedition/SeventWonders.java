package fr.utbm.swutbmedition;

import fr.utbm.swutbmedition.view.MainFrame;
import javafx.application.Application;
import javafx.stage.Stage;

public class SeventWonders extends Application {
    public static void main(String [] args) {    	
    	Application.launch(SeventWonders.class,args);
    }

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("7 Wonders | UTBM Edition");
		primaryStage.setResizable(false);
		
		MainFrame frame = new MainFrame(primaryStage);
		frame.showMenuFrame();
		
		primaryStage.show();
	}

}