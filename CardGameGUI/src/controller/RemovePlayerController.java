package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AlertingBox;
import view.CardGameStatusBar;
import view.PlayersComboBox;
import view.SummaryPanel;
import view.model.IDTracker;
import view.model.PlayerState;

//The function of this class is to handle the remove player button

public class RemovePlayerController implements ActionListener 
{
	private GameEngine engine;
	private PlayersComboBox combo;
	private SummaryPanel panel;
	private CardGameStatusBar status_bar;
	private AlertingBox alert;
	private PlayerState states;
	private AddPlayerController controller;
	private IDTracker tracker;
	
	public RemovePlayerController(GameEngine engine, PlayersComboBox combo, SummaryPanel panel,CardGameStatusBar status_bar,PlayerState states, IDTracker tracker)
	{
		this.engine = engine;	
		this.combo = combo;
		this.panel = panel;
		this.status_bar = status_bar;
		this.states = states;
		this.tracker = tracker;
		alert = new AlertingBox();
	}
	
	public void actionPerformed(ActionEvent buttonPress) 
	{
		Player removal = (Player) combo.getSelectedItem();
		if(removal.getPlayerId().equals("0"))
			alert.showAlert("Alert!", "Nice Try :). House cannot be removed!");
		else if(hasPlayerBet())
			alert.showAlert("Removing Player!","You cannot remove a player from a game once someone is being dealt or has already dealt");
		else
		{
			engine.removePlayer(removal);
			combo.removeItem((Player) combo.getSelectedItem());
			states.remove(removal);
			tracker.removeIdToTrack(Integer.parseInt(removal.getPlayerId()));
			status_bar.currentStatus(removal.getPlayerName() + " has been removed");
			panel.update();
		}

	}
	
	private boolean hasPlayerBet()
	{
		HashMap<Player,Boolean> playersThatHaveDealt = states.getDealt();
		if(playersThatHaveDealt != null && playersThatHaveDealt.containsValue(true))
			return true;
		return false;
	}

}
