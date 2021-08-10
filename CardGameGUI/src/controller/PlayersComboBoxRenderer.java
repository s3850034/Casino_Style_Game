package controller;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.SimplePlayer;
import model.interfaces.Player;
//The purpose of this class is to show the name of the player in the combo box

public class PlayersComboBoxRenderer extends DefaultListCellRenderer 
{

	@Override
	public Component getListCellRendererComponent(JList combo, Object obj, int index , boolean arg3, boolean arg4) 
	{
		
		if (obj instanceof Player)
		{
			obj = ((Player)obj).getPlayerName();
		}
		super.getListCellRendererComponent(combo, obj, index, arg3, arg4);
		return this;
		
	}

}
