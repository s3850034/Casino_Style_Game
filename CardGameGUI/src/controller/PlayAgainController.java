package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AlertingBox;
import view.CardGameStatusBar;
import view.CardPanel;
import view.PlayerInputPane;
import view.PlayersComboBox;
import view.SummaryPanel;
import view.model.PlayerState;

//The purpose of this class is to let the user play again once everyone including the House has dealt

public class PlayAgainController implements ActionListener
{
	private CardGameStatusBar status_bar;
	private PlayerState states;
	private AlertingBox alert;
	private CardPanel panel;
	private GameEngine engine;
	private SummaryPanel sumPanel;
	
	public PlayAgainController(GameEngine engine,CardGameStatusBar status_bar,PlayerState states,CardPanel panel,SummaryPanel sumPanel)
	{
		alert = new AlertingBox();
		this.status_bar = status_bar;
		this.states = states;
		this.panel = panel;
		this.engine = engine;
		this.sumPanel = sumPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(states.getFinishRound())
		{
			states.resetPlayerState();
			status_bar.currentStatus("Next Round Can Begin Now");
			panel.haveStartedAgain();
			for (Player player: engine.getAllPlayers())
				player.setResult(0);
			sumPanel.update();
			
		}
		else
		{
			alert.showAlert("Cannot press this", "Cannot play again until a round has not finished!");
		}
		
	}
	
	

}
