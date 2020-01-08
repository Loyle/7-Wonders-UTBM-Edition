package fr.utbm.swutbmedition.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import fr.utbm.swutbmedition.controller.MenuController;
import fr.utbm.swutbmedition.model.Player;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuFrame extends BorderPane {
    private MenuController menuController;
    private MainFrame mainFrame;
    
    private BorderPane layoutMenu;
    private VBox layoutCenter;
    private VBox layoutSettings;
    private VBox layoutBottom;
    
    private VBox playersList;

    public MenuFrame(MainFrame mainFrame) {
    	this.mainFrame = mainFrame;
    	this.menuController = new MenuController(this);
    	
    	try {
			this.initFrame();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void initFrame() throws FileNotFoundException {
    	Text title = new Text("UTBM Edition");
    	title.setFont(Font.font(40));
    	title.setTextAlignment(TextAlignment.CENTER);
    	
    	FileInputStream inputImage = new FileInputStream("data/images/logo7w.PNG");
    	Image logoImage = new Image(inputImage);
    	ImageView viewLogoImage = new ImageView(logoImage);
    	viewLogoImage.fitWidthProperty().bind(mainFrame.getPrimaryStage().widthProperty());
    	
    	Button startButton = new Button("Démarrer");
    	startButton.setMinSize(200, 50);
    	startButton.setOnAction(e -> mainFrame.showGameFrame(this.menuController.getPlayers()));
    	
    	VBox playerAddition = new VBox();
    	playerAddition.setAlignment(Pos.CENTER);
    	playerAddition.setSpacing(20);
    	
    	HBox playerInput = new HBox();
    	playerInput.setAlignment(Pos.CENTER);
    	playerInput.setSpacing(10);
    	
    	TextField textfield = new TextField();
    	textfield.setPrefSize(300, 50);
    	playerInput.getChildren().add(textfield);
    	
    	Button addButton = new Button("Ajouter");
    	addButton.setMinSize(100, 50);
    	addButton.setOnAction(e -> {
    		if(!textfield.getText().equals("")) {
    			menuController.addPlayer(new Player(textfield.getText())); 
    			textfield.clear();
    		}
    	});
    	playerInput.getChildren().add(addButton);
    	
    	playerAddition.getChildren().add(playerInput);
    	
    	this.playersList = new VBox();
    	this.playersList.setAlignment(Pos.CENTER);
    	this.playersList.setSpacing(10);
    	
    	playerAddition.getChildren().add(this.playersList);
    	
    	Button closeButton = new Button("Quitter");
    	closeButton.setMinSize(150, 40);
    	closeButton.setOnAction(e -> mainFrame.getPrimaryStage().close());
    	
    	layoutBottom = new VBox(closeButton);
    	layoutBottom.setAlignment(Pos.TOP_RIGHT);
    	layoutBottom.setPadding(new Insets(0,40,30,0));
    	
    	layoutMenu = new BorderPane();
    	layoutCenter = new VBox(viewLogoImage,title,startButton,playerAddition);
    	layoutCenter.setSpacing(30);
    	layoutCenter.setAlignment(Pos.CENTER);
    	layoutMenu.setCenter(layoutCenter);
    	layoutMenu.setBottom(layoutBottom);
    	
    	title = new Text("Paramètres");
    	title.setX(60);
    	title.setTextAlignment(TextAlignment.CENTER);
    	
    	Button saveButton = new Button("Sauvegarder");
    	saveButton.setMinSize(200, 50);
    	
    	Button backButton = new Button("Retour");
    	backButton.setMinSize(200, 50);
    	backButton.setOnAction(e -> menuController.onBackBtnClick());
    	
    	HBox btnLayout = new HBox(saveButton,backButton);
    	btnLayout.setAlignment(Pos.CENTER);
    	btnLayout.setSpacing(50);
    	
    	layoutSettings = new VBox(title,btnLayout);
    	layoutSettings.setSpacing(30);
    	layoutSettings.setAlignment(Pos.CENTER);
    }
    public void showMenu() {
    	this.setCenter(layoutMenu);
    }
    
    public void showSettings() {
    	this.setCenter(layoutSettings);    	
    }
    
    public void refreshPlayers() {
    	this.playersList.getChildren().clear();
    	for(Player player : this.menuController.getPlayers()) {
    		HBox p = new HBox();
    		p.setAlignment(Pos.CENTER);
    		p.setSpacing(10);
    		
    		Text name = new Text(player.getName());
    		name.setFont(Font.font(18));
    		
    		Button remove = new Button("X");
    		remove.setOnMouseClicked(e -> {
    			menuController.removePlayer(player);
    		});
    		
    		p.getChildren().addAll(name,remove);
    		
    		this.playersList.getChildren().add(p);
    	}
    }
}