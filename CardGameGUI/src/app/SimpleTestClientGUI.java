package app;

import java.io.IOException;
import java.util.Deque;
import java.util.logging.Level;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

import view.GameEngineCallbackImpl;
import view.model.PlayerState;
import view.CardGameFrame;
import view.GameEngineCallbackGUI;

//Purpose - the purpose of this class is to run the Card Game

public class SimpleTestClientGUI
{
   public static void main(String args[]) throws IOException
   {
	   //If you wish to test the delays then you can adjust them in the DealController Class - for assessor
	   
	   GameEngine engine = new GameEngineImpl();
	   PlayerState states = new PlayerState();
	   CardGameFrame frame = new CardGameFrame(engine,states);
	   frame.createFrame();
	   engine.addGameEngineCallback(new GameEngineCallbackImpl());
	   GameEngineCallbackImpl.setAllHandlers(Level.INFO, GameEngineCallbackImpl.logger, true);
	   engine.addGameEngineCallback(new GameEngineCallbackGUI(frame,states));
	   
   }

}
