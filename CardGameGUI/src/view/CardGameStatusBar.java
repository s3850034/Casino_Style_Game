package view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

//this class is the status bar that constantly updates the happenings of the game

public class CardGameStatusBar extends JLabel
{
	public CardGameStatusBar()
	{
		super();
		super.setPreferredSize(new Dimension(150,12));
		currentStatus("default status");
	}
	
	public void currentStatus(String status)
	{
		setText(status);
	}
}
