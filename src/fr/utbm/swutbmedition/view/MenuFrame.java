package fr.utbm.swutbmedition.view;

import fr.utbm.swutbmedition.controller.MenuController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MenuFrame extends Scene {
    private MenuController menuController;
    private MainFrame mainFrame;
    private BorderPane root;
    
    private VBox layoutMenu;
    private VBox layoutSettings;

    public MenuFrame(MainFrame mainFrame, BorderPane root) {
    	super(root,800,600);
    	
    	this.root = root;
    	this.mainFrame = mainFrame;
    	this.menuController = new MenuController(this);
    	
    	this.initFrame();
    }
    public void initFrame() {
    	Text title = new Text("7 Wonders UTBM Edition");
    	title.setX(60);
    	title.setTextAlignment(TextAlignment.CENTER);
    	
    	Button startButton = new Button("Démarrer");
    	startButton.setMinSize(200, 50);
    	startButton.setOnMouseClicked(new EventHandler<MouseEvent >() {
    		public void handle(MouseEvent e) {
    			mainFrame.showGameFrame();
    		}
		});
    	
    	Button settingButton = new Button("Paramètres");
    	settingButton.setMinSize(200, 50);
    	settingButton.setOnMouseClicked(new EventHandler<MouseEvent >() {
    		public void handle(MouseEvent e) {
    			menuController.onSettingBtnClick();
    		}
		});
    	
    	layoutMenu = new VBox(title,startButton,settingButton);
    	layoutMenu.setSpacing(30);
    	layoutMenu.setAlignment(Pos.CENTER);
    	
    	
    	title = new Text("Paramètres");
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
    	btnLayout.setAlignment(Pos.CENTER);
    	btnLayout.setSpacing(50);
    	
    	layoutSettings = new VBox(title,btnLayout);
    	layoutSettings.setSpacing(30);
    	layoutSettings.setAlignment(Pos.CENTER);
    }
    public void showMenu() {
    	root.setCenter(layoutMenu);
    }
    
    public void showSettings() {
    	root.setCenter(layoutSettings);    	
    }
}