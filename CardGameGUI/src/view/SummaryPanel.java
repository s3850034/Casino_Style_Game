package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.model.IDTracker;
import view.model.PlayerState;

//This class serves as the information panel - summarizes every players happenings in the game
public class SummaryPanel extends JPanel
{
	private JTable player_table;
	private DefaultTableModel dynamic_table;
	private String [] table_columns = {"Id","Name","Balance","Bet Placed","Result","Win/Loss"};
	private GameEngine engine;
	private PlayersComboBox combo;
	private PlayerState states;
    private HashMap<Player,String> interimWins;
    private IDTracker tracker;
    
    public SummaryPanel(GameEngine engine, PlayersComboBox combo, PlayerState states,Dimension dimension,IDTracker tracker)
	{
		this.tracker = tracker;
		interimWins = new HashMap<Player,String>();
		this.engine = engine;
		this.combo = combo;
		this.states = states;
		setBorder(BorderFactory.createTitledBorder("Summary Panel"));
		setLayout(new BorderLayout());
		player_table = new JTable();
		dynamic_table = new DefaultTableModel(0,0);
		dynamic_table.setColumnIdentifiers(table_columns);
		player_table.setModel(dynamic_table);
		add(new JScrollPane(player_table));
	}
	
	public void update()
	{
		if(player_table.getModel().getRowCount() == 0)
			addAll();
		else
		{
			removeRows();
			addAll();
		}

	}
	
	private void addAll()
	{
		Collection<Player> players = engine.getAllPlayers();
		dynamic_table = (DefaultTableModel) player_table.getModel();
		for (Player player : players)
		{
			if(interimWins.get(player) == null)
			{
				dynamic_table.addRow(new Object[] {player.getPlayerId(),player.getPlayerName(),
						player.getPoints(),player.getBet(),player.getResult(),""});
			}
			else
			{
				dynamic_table.addRow(new Object[] {player.getPlayerId(),player.getPlayerName(),
						player.getPoints(),player.getBet(),player.getResult(),interimWins.get(player)});
			}
		}
	}
	
	private void removeRows()
	{
		dynamic_table = (DefaultTableModel) player_table.getModel();
		int rows = dynamic_table.getRowCount();
		for (int end = rows-1; end >= 0; end--)
			dynamic_table.removeRow(end);
 	}

	public void checkResults(int result, GameEngine engine)
	{
		HashMap<Player, Integer> bets = states.getPreviousPoints();
		int houseResult = result;
		Collection<Player> players = engine.getAllPlayers();
		ArrayList<Player> removed = new ArrayList<Player>();
		for (Player player: players)
		{
			if (player.getPoints() == 0)
			{
			  removed.add(player);
			
			}
			    
		}
		
		if(!removed.isEmpty())
		{
			for (Player player: removed)
			{
				engine.removePlayer(player);
				combo.removeItem( (Player) player);
				tracker.removeIdToTrack(Integer.parseInt(player.getPlayerId()));	
				interimWins.remove(player);
			}
		}
		
		for (Player player: engine.getAllPlayers())
		{
			    
				if(houseResult < player.getResult())
				{
					if(interimWins.containsKey(player))
						interimWins.replace(player,"WON " + bets.get(player));
					else
						interimWins.put(player,"WON " + bets.get(player));
				}
				else if (houseResult > player.getResult())
				{
					if(interimWins.containsKey(player))
						interimWins.replace(player,"LOST " + bets.get(player));
					else
						interimWins.put(player,"LOST " + bets.get(player));
				}
				else if(houseResult == player.getResult())
				{
					if(interimWins.containsKey(player))
						interimWins.replace(player,"DREW");
					else
						interimWins.put(player,"DREW");
				}
				
		}
		
		update();
			
	}
	
	
}
