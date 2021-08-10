package view;

import java.util.ArrayList;

import javax.swing.JComboBox;

import controller.PlayersComboBoxRenderer;
import model.SimplePlayer;
import model.interfaces.Player;

//this combo box class stores all the players in the game (inlcuding the House)
public class PlayersComboBox extends JComboBox
{
	JComboBox<Player> playersList;
	private Player house;
	public PlayersComboBox()
	{
		playersList = new JComboBox<Player>();
		house = new SimplePlayer("0","House",0);
		addItem(house);
		setRenderer(new PlayersComboBoxRenderer());
	}
	
	public Player getHouse()
	{
		return house;
	}
	
	
	
	
}
