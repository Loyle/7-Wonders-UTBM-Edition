package fr.utbm.swutbmedition.view;

import fr.utbm.swutbmedition.controller.GameController;
import fr.utbm.swutbmedition.model.Game;
import fr.utbm.swutbmedition.model.Player;

public class GameFrame {
    private Game game;

    private GameController gameController;


    public GameFrame() {
        // TODO implement here
    	this.game = new Game();
    	this.gameController = new GameController(this, this.game);
    	
    	this.gameController.start();
    }


	public void displayBoard(Player currentPlayer) {
		// Afficher le boad du player		
	}

}