package view;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;
import view.model.PlayerState;

//this class serves as the communicator between the game engine and the GUI project

public class GameEngineCallbackGUI implements GameEngineCallback 
{
	CardGameFrame frame;
	CardPanel cardPanel;
	SummaryPanel sumPanel;
	CardGameStatusBar statusBar;
	PlayerState states;
	
	public GameEngineCallbackGUI(CardGameFrame frame,PlayerState states)
	{

		this.frame = frame;
		cardPanel = frame.getMainPanel().getCardPanel();
		sumPanel = frame.getMainPanel().getSumPanel();
		statusBar = frame.getStatusBar();
		this.states = states;
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				cardPanel.playerUpdate(player,card);
				cardPanel.repaint();
				statusBar.currentStatus(player.getPlayerName() + " has been dealt " + card);
				
			}
		});

	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				cardPanel.playerBustUpdate(player,card);
				cardPanel.repaint();
				statusBar.currentStatus(player.getPlayerName() + " has busted");
			}
		});

	}

	@Override
	public void result(Player player, int result, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				sumPanel.update();
			}
		});

	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				cardPanel.houseUpdate(card);
				cardPanel.repaint();
				statusBar.currentStatus("House has been dealt " + card);
			}
		});

	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				cardPanel.houseBustUpdate(card);
				cardPanel.repaint();
				statusBar.currentStatus("House has busted");
			}
		});


	}

	@Override
	public void houseResult(int result, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				sumPanel.checkResults(result,engine);
				states.setFinishRound(true);
			}
		});

	}

}
