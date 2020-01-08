package fr.utbm.swutbmedition.controller;

import java.util.ArrayList;

import fr.utbm.swutbmedition.model.Player;
import fr.utbm.swutbmedition.view.MenuFrame;

public class MenuController {
    private MenuFrame menuFrame;
    private ArrayList<Player> players = new ArrayList<Player>();
    
    public MenuController(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }
    
    public void onSettingBtnClick() {
    	this.menuFrame.showSettings();
    }

	public void onBackBtnClick() {
		this.menuFrame.showMenu();		
	}
	
	public void addPlayer(Player player) {
		if(this.players.size() < 7) {
			this.players.add(player);
			this.menuFrame.refreshPlayers();			
		}
	}
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public void removePlayer(Player player) {
		this.players.remove(player);
		this.menuFrame.refreshPlayers();
	}
}