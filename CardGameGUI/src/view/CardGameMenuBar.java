package view;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerController;
import controller.PlayAgainController;
import controller.RemovePlayerController;
import model.interfaces.GameEngine;
import view.model.PlayerState;

//this class serves as the menubar for the person to play again
public class CardGameMenuBar extends JMenuBar
{
	
	public CardGameMenuBar(GameEngine engine,CardGameStatusBar statusBar, PlayerState status, CardPanel panel, SummaryPanel sumPanel)
	{
		JMenu playerFunctions = new JMenu("Menu");
		add(playerFunctions);
		
		JMenuItem playAgain = new JMenuItem("Play Again");
		
		playerFunctions.add(playAgain);
		
		PlayAgainController controller = new PlayAgainController(engine,statusBar,status,panel,sumPanel);
		playAgain.addActionListener(controller);
		
		
			
	}

}
