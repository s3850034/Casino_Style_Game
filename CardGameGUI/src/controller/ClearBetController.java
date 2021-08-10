package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.BetBox;
import view.CardGameStatusBar;
import view.PlayersComboBox;
import view.SummaryPanel;
import view.model.PlayerState;
import view.AlertingBox;

//The purpose of this class is to handle the clear bet button functionality

public class ClearBetController implements ActionListener
{
	private GameEngine engine;
	private PlayersComboBox combo;
	private SummaryPanel panel;
	private CardGameStatusBar status_bar;
	private PlayerState states;
	private AlertingBox alert;
	
	public ClearBetController(GameEngine engine, PlayersComboBox combo, SummaryPanel panel,CardGameStatusBar status_bar, PlayerState states)
	{
		this.engine = engine;	
		this.combo = combo;
		this.panel = panel;
		this.status_bar = status_bar;
		this.states = states;
		alert = new AlertingBox();
	}
	
	public void actionPerformed(ActionEvent buttonPress) 
	{
		Player bettingPlayer = (Player) combo.getSelectedItem();
		HashMap<Player,Boolean> playerBets = states.getBets();
		HashMap<Player,Boolean> hasThisPlayerDealt = states.getDealt();
		
		if(bettingPlayer.getPlayerId().equals("0"))
			alert.showAlert("Alert!", "House cannot place a bet!");
		else if(!playerBets.get(bettingPlayer))
		{
			alert.showAlert("Betting Alert!", "Selected player has not bet to begin with");
		}
		else if(hasThisPlayerDealt.get(bettingPlayer))
			alert.showAlert("Too Late!","This player cannot change his bet after dealing");
		else
		{
			bettingPlayer.resetBet();
			status_bar.currentStatus(bettingPlayer.getPlayerName() + " has reset their bet");
			states.hasBet(bettingPlayer,false);
			panel.update();
		}

	}
}
