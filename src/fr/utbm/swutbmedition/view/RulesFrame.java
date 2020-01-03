package fr.utbm.swutbmedition.view;

import fr.utbm.swutbmedition.controller.RulesController;

public class RulesFrame {
	private MainFrame mainFrame;
    private RulesController rulesController;

    public RulesFrame(MainFrame mainFrame) {
    	this.mainFrame = mainFrame;
    	
        this.rulesController = new RulesController();
    }

}