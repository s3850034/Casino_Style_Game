package view.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import model.interfaces.GameEngine;
import model.interfaces.Player;

//This class essentially was created for the purposes of having the program user follow the rules of the GUI.
//Stores instances of the player - if they have been dealt, if they have added, if they have won, etc.
public class PlayerState 
{
	private HashMap<Player, Boolean> dealt;
	private HashMap<Player, Boolean> bet;
	private HashMap<Player, Boolean> won;
	private HashMap<Player, Boolean> drew;
	private HashMap<Player, Integer> previousPoints;
	private boolean finishedRound = false;
	
	public PlayerState()
	{
		dealt = new HashMap<Player,Boolean>();
		bet = new HashMap<Player,Boolean>();
		won = new HashMap<Player,Boolean>();
		drew = new HashMap<Player,Boolean>();
		previousPoints = new HashMap<Player,Integer>();
	}
	
	public void addPlayerState(Player player)
	{
		dealt.put(player, false);
		bet.put(player, false);
		won.put(player, false);
		drew.put(player, false);
		previousPoints.put(player,0);
	}
	public void remove(Player player)
	{
		dealt.remove(player);
		bet.remove(player);
		won.remove(player);
		drew.remove(player);
		previousPoints.remove(player);
	}
	public void resetPlayerState()
	{
		for(Player putPlayer: dealt.keySet())
			dealt.replace(putPlayer, false);
		for(Player putPlayer: bet.keySet())
			bet.replace(putPlayer, false);
		for(Player putPlayer: won.keySet())
			won.replace(putPlayer, false);
		for(Player putPlayer: drew.keySet())
			drew.replace(putPlayer, false);
		for(Player putPlayer: previousPoints.keySet())
			previousPoints.replace(putPlayer, 0);
		finishedRound = false;
	}
	public void hasWon(Player player, boolean value)
	{
		won.replace(player,value);
	}
	
	public void hasDrawed(Player player)
	{
		drew.replace(player,true);
	}
	
	public void hasBet(Player player, boolean value)
	{
		bet.replace(player, value);
	}
	
	public void hasDealt(Player player, boolean value)
	{
		dealt.replace(player, value);
	}
	
	public void previousPoints(Player player,int points)
	{
		previousPoints.replace(player, points);
	}
	
	public HashMap<Player,Boolean> getDealt()
	{
		return dealt;
	}
	
	public HashMap<Player,Boolean> getBets()
	{
		return bet;
	}
	
	public HashMap<Player,Boolean> getWinCond()
	{
		return won;
	}
	
	public HashMap<Player,Boolean> getDrew()
	{
		return drew;
	}
	
	public HashMap<Player, Integer> getPreviousPoints()
	{
		return previousPoints;
	}
	
	public boolean getFinishRound()
	{
		return finishedRound;
	}
	
	public void setFinishRound(boolean value)
	{
		finishedRound = value;
	}
	

	

}
