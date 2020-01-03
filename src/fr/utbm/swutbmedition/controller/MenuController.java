package fr.utbm.swutbmedition.controller;

import fr.utbm.swutbmedition.view.MenuFrame;

public class MenuController {
    private MenuFrame menuFrame;
    
    
    public MenuController(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }
    
    public void onSettingBtnClick() {
    	this.menuFrame.showSettings();
    }

	public void onBackBtnClick() {
		this.menuFrame.showMenu();		
	}
}