package fr.utbm.swutbmedition.view;

import fr.utbm.swutbmedition.controller.MenuController;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MenuFrame extends Parent {
    private MenuController menuController;

    public MenuFrame() {
    	this.menuController = new MenuController();
    	
    	// Ici faire un layout sé + propre
    	Text title = new Text("7 Wonders UTBM Edition");
    	title.setX(60);
    	title.setTextAlignment(TextAlignment.CENTER);
    	this.getChildren().add(title);
    	
    	Button startButton = new Button("Démarrer");
    	startButton.setMinSize(200, 50);
    	startButton.setTranslateX(50);
    	startButton.setTranslateY(50);
    	this.getChildren().add(startButton);
    	
    	Button settingButton = new Button("Paramètres");
    	settingButton.setMinSize(200, 50);
    	settingButton.setTranslateX(50);
    	settingButton.setTranslateY(150);
    	this.getChildren().add(settingButton);
    	
    	
    }

}