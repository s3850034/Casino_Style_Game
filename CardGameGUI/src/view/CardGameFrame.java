package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;


import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.model.IDTracker;
import view.model.PlayerState;

//this is the main frame for the game, all the the view of the program is through here

public class CardGameFrame extends JFrame 
{
	private CardGameToolBar toolBar;
	private CardGameMenuBar menuBar;
	private CardGameStatusBar statusBar;
	private MainPanel gamePanel;
	private GameEngine engine;
	private SummaryPanel sumPanel;
	private CardPanel cardPanel;
	private PlayersComboBox players;
	private PlayerState states;
	private IDTracker tracker;
	public CardGameFrame(GameEngine engine,PlayerState states) throws IOException
	{
		super("Casino Style Card Game");
		this.engine = engine;
		this.states = states;
		tracker = new IDTracker();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) dimension.getWidth();
		int height = (int) dimension.getHeight();
        setMinimumSize(new Dimension((int) (width * 0.45), (int) (height * 0.45)));
		players = new PlayersComboBox();
		cardPanel = new CardPanel(this,dimension);
		sumPanel = new SummaryPanel(engine,players,states,dimension,tracker);
		statusBar = new CardGameStatusBar();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void createFrame()
	{
		toolBar = new CardGameToolBar(engine,sumPanel, cardPanel,statusBar,players,states,tracker);
		menuBar = new CardGameMenuBar(engine,statusBar,states,cardPanel,sumPanel);
		setJMenuBar(menuBar);
		add(toolBar,BorderLayout.PAGE_START);
		gamePanel = new MainPanel(sumPanel,cardPanel);
		add(gamePanel);
		add(statusBar,BorderLayout.SOUTH);
		setVisible(true);
		
	}
	
	public MainPanel getMainPanel()
	{
		return gamePanel;
	}
	
	public CardGameStatusBar getStatusBar()
	{
		return statusBar;
	}
	
	public PlayersComboBox getPlayersBox()
	{
		return players;
	}
	
	public PlayerState getStates()
	{
		return states;
	}
}
