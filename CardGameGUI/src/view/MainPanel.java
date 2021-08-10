package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.interfaces.GameEngine;

//This main panel stores the two important panels of the game - Summary Panel and CardPanel
public class MainPanel extends JPanel
{
	private CardPanel cardPanel;
	private SummaryPanel summaryPanel;
	
	public MainPanel(SummaryPanel summaryPanel,CardPanel cardPanel)
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.summaryPanel = summaryPanel;
		add(summaryPanel);
		this.cardPanel = cardPanel;
		add(cardPanel);
	}
	
	public CardPanel getCardPanel()
	{
		return cardPanel;
	}
	
	public SummaryPanel getSumPanel()
	{
		return summaryPanel;
	}

}
