package fr.utbm.swutbmedition.view;

import fr.utbm.swutbmedition.controller.MenuController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MenuFrame extends Parent {
    private MenuController menuController;

    public MenuFrame() {
    	this.menuController = new MenuController(this);
    	showMainMenu();
    }
    public void showMainMenu() {
    	this.getChildren().clear();
    	Text title = new Text("7 Wonders UTBM Edition");
    	title.setX(60);
    	title.setTextAlignment(TextAlignment.CENTER);
    	
    	Button startButton = new Button("Démarrer");
    	startButton.setMinSize(200, 50);
    	startButton.setOnMouseClicked(new EventHandler<MouseEvent >() {
    		public void handle(MouseEvent e) {
    			new GameFrame();
    		}
		});
    	
    	Button settingButton = new Button("Paramètres");
    	settingButton.setMinSize(200, 50);
    	settingButton.setOnMouseClicked(new EventHandler<MouseEvent >() {
    		public void handle(MouseEvent e) {
    			menuController.onSettingBtnClick();
    		}
		});
    	
    	VBox layout = new VBox(title,startButton,settingButton);
    	layout.setSpacing(30);
    	layout.setAlignment(Pos.CENTER);
    	this.getChildren().add(layout);
    }
    
    public void showSettings() {
    	this.getChildren().clear();
    	Text title = new Text("Paramètres");
    	title.setX(60);
    	title.setTextAlignment(TextAlignment.CENTER);
    	
    	Button saveButton = new Button("Sauvegarder");
    	saveButton.setMinSize(200, 50);
    	
    	Button backButton = new Button("Retour");
    	backButton.setMinSize(200, 50);
    	backButton.setOnMouseClicked(new EventHandler<MouseEvent >() {
    		public void handle(MouseEvent e) {
    			menuController.onBackBtnClick();
    		}
		});
    	HBox btnLayout = new HBox(saveButton,backButton);
    	btnLayout.setSpacing(50);
    	
    	VBox layout = new VBox(title,btnLayout);
    	layout.setSpacing(30);
    	layout.setAlignment(Pos.CENTER);
    	this.getChildren().add(layout);
    }

}