package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AlertingBox;
import view.CardGameStatusBar;
import view.PlayerInputPane;
import view.PlayersComboBox;
import view.SummaryPanel;
import view.model.IDTracker;
import view.model.PlayerState;

//The purpose of this class is to handle the add button functionality

public class AddPlayerController implements ActionListener
{
	private GameEngine engine;
	private PlayerInputPane player_details;
	private PlayersComboBox combo;
	private SummaryPanel panel;
	private CardGameStatusBar status_bar;
	private PlayerState states;
	private AlertingBox alert;
	private ArrayList<Integer> idTracker;
	private IDTracker tracker;
	
	public AddPlayerController(GameEngine engine, PlayersComboBox combo, SummaryPanel panel,CardGameStatusBar status_bar,PlayerState states, IDTracker tracker)
	{
		this.tracker = tracker;
		this.engine = engine;	
		this.combo = combo;
		this.panel = panel;
		this.status_bar = status_bar;
		this.states = states;
		alert = new AlertingBox();
		idTracker = new ArrayList<Integer>();
	}
	
	public void actionPerformed(ActionEvent buttonPress) 
	{
		if(hasPlayerBet())
			alert.showAlert("Adding Player!","You cannot add a new player to a game once someone is being dealt or has already has dealt");
		else
		{
			
			player_details = new PlayerInputPane();
			String[] row = player_details.getRow();
			if (row == null)
				alert.showAlert("No player", "You added no player at all");
			else 
			{
				boolean flag = true;
				if(!tracker.getIDTracker().isEmpty())
				{
					if(tracker.containsID(Integer.parseInt(row[0])))
					{
						alert.showAlert("Adding Player!","You cannot add a player that already exists in the game with same ID");
						flag = false;
					}
						
				}
				if(flag)
				{
					Player playerPart = new SimplePlayer(row[0],row[1],Integer.parseInt(row[2]));
					engine.addPlayer(playerPart);
					states.addPlayerState(playerPart);
					status_bar.currentStatus(row[1] + " has successfully been added");
					panel.update();
					tracker.addIdToTrack(Integer.parseInt(row[0]));
					combo.addItem(playerPart);
					combo.setRenderer(new PlayersComboBoxRenderer());
					combo.setSelectedItem(playerPart);
				}
				
			}
			
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
