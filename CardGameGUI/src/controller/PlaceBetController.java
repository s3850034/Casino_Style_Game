package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AlertingBox;
import view.BetBox;
import view.CardGameStatusBar;
import view.PlayersComboBox;
import view.SummaryPanel;
import view.model.PlayerState;

//The purpose of this class is to handle the functionality of the place bet button

public class PlaceBetController implements ActionListener
{
	private GameEngine engine;
	private BetBox betting_details;
	private PlayersComboBox combo;
	private SummaryPanel panel;
	private CardGameStatusBar status_bar;
	private PlayerState states;
	private AlertingBox alert;
	
	public PlaceBetController(GameEngine engine, PlayersComboBox combo, SummaryPanel panel,CardGameStatusBar status_bar, PlayerState states)
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
		HashMap <Player,Boolean> hasThisPlayerDealt = states.getDealt();
		
		if(bettingPlayer.getPlayerId().equals("0"))
			alert.showAlert("Alert!", "House cannot place a bet!");
		else if(hasThisPlayerDealt.get(bettingPlayer) == true)
			alert.showAlert("Too Late!", "This player cannot change their bet now after dealing");
		else
		{		
			betting_details = new BetBox(combo);
			String betAmount = betting_details.getBettingAmount();
			if(betAmount == null)
				alert.showAlert("No bet placed", "You placed no bet");
			else 
			{
				int betNewAmount = Integer.parseInt(betAmount);
				engine.placeBet(bettingPlayer, betNewAmount);
				states.hasBet(bettingPlayer,true);
				states.previousPoints(bettingPlayer,betNewAmount);
				status_bar.currentStatus(bettingPlayer.getPlayerName() + " has placed a bet of " + betAmount);
				panel.update();
			}
		}
	}
}