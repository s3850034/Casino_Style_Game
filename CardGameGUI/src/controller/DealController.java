package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AlertingBox;
import view.CardGameStatusBar;
import view.CardPanel;
import view.PlayersComboBox;
import view.model.PlayerState;

//The purpose of this class is to handle the deal functionality

public class DealController implements ActionListener
{
	private GameEngine engine;
	private PlayersComboBox combo;
	private CardPanel panel;
	private PlayerState states;
	private AlertingBox alert;
	
	public DealController(GameEngine engine,PlayersComboBox combo,CardPanel panel,PlayerState states)
	{
		this.engine = engine;	
		this.combo = combo;
		this.panel = panel;
		this.states = states;
		panel.signal(combo);
		alert = new AlertingBox();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Player dealingPlayer = (Player) combo.getSelectedItem();
		HashMap<Player,Boolean> playerBets = states.getBets();
		HashMap<Player,Boolean> playerDealed = states.getDealt();
		
		if(dealingPlayer.getPlayerId().equals("0"))
			alert.showAlert("Alert!", "House cannot manually deal!");
		else if(!playerBets.get(dealingPlayer))
		{
			alert.showAlert("Dealing Alert!", "Selected player has not bet to begin with");
		}
		else if(playerDealed.get(dealingPlayer))
			alert.showAlert("Dealing Again!", "Player cannot be dealt again this round!");
		else 
		{
			states.hasDealt(dealingPlayer,true);
			new Thread()
			{
				@Override
				public void run()
				{
					engine.dealPlayer(dealingPlayer, 100);
					if(everyPlayerHasDealed(playerDealed))
					{
						combo.setSelectedIndex(0);
						engine.dealHouse(100);
					}
				}
			}.start();
			
		}

	}
	
	public boolean everyPlayerHasDealed(HashMap<Player,Boolean> states)
	{
		boolean dealtCounter = false;
		for(Boolean value : states.values())
		{
			if(value)
				dealtCounter = true;
			else
				return false;
		}
		return dealtCounter;
	}

}
