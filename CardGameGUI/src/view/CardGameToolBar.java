package view;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import controller.AddPlayerController;
import controller.ClearBetController;
import controller.DealController;
import controller.PlaceBetController;
import controller.PlayersComboBoxRenderer;
import controller.RemovePlayerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.model.IDTracker;
import view.model.PlayerState;

//this class creates a toolbar that contains all of the buttons of interest
public class CardGameToolBar extends JToolBar
{
	private JButton btnAddPlayer = new JButton("Add Player");
	private JButton btnRemPlayer = new JButton("Remove Player");
	private JButton btnBet = new JButton("Place Bet");
	private JButton btnRemBet = new JButton("Cancel Bet");
	private JButton btnDeal = new JButton("Deal");
	private PlayersComboBox players;
	private PlayerState states;
	private IDTracker tracker;
	public CardGameToolBar(GameEngine engine, SummaryPanel sumPanel, CardPanel cardPanel, CardGameStatusBar statusBar,PlayersComboBox players,PlayerState states,IDTracker tracker)
	{
		
		this.players = players;
		this.states = states;
		this.tracker = tracker;
		//Add main function buttons
		add(btnAddPlayer);
		add(btnBet);
		add(btnDeal);
		
		//add ComboBox
		add(this.players);
		//add(btnRemPlayer);
		
		//add secondary function buttons
		add(btnRemBet);	
		add(btnRemPlayer);
		
		PlaceBetController btnBetController = new PlaceBetController(engine, players, sumPanel,statusBar,states);
		btnBet.addActionListener(btnBetController);
	
		ClearBetController btnRemBetController = new ClearBetController(engine,players,sumPanel,statusBar,states);
		btnRemBet.addActionListener(btnRemBetController);
		
		DealController btnDealController = new DealController(engine,players,cardPanel,states);
		btnDeal.addActionListener(btnDealController);
		
		AddPlayerController menuAddController = new AddPlayerController(engine, players, sumPanel,statusBar,states,tracker);
		btnAddPlayer.addActionListener(menuAddController);
		
		RemovePlayerController menuRemController = new RemovePlayerController(engine,players,sumPanel,statusBar,states,tracker);
		btnRemPlayer.addActionListener(menuRemController);
	}
	
}
