package model;

import model.interfaces.Player;

public class SimplePlayer implements Player
{
	private String id;
	private String playerName;
	private int points;
	private int bet;
	private int RESULT;
	
	public SimplePlayer(String id, String playerName, int initialPoints)
	{
		this.id = id;
		this.playerName = playerName;
		this.points = initialPoints;
	}
	
	public String getPlayerName()
	{
		return this.playerName;
	}
	
	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}
	
	public int getPoints() 
	{
		return this.points;
	}

	@Override
	public void setPoints(int points) 
	{
		this.points = points;
	}

	@Override
	public String getPlayerId() 
	{
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public boolean setBet(int bet) 
	{
		// TODO Auto-generated method stub
		if (bet > 0 && bet <= this.points)
		{
			this.bet = bet;
			return true;
		}
		else
		{
			resetBet();
			return false;
		}
	}

	@Override
	public int getBet() 
	{
		return this.bet;
	}

	@Override
	public void resetBet() 
	{
		this.bet = 0;
	}

	@Override
	public int getResult() 
	{
		// TODO Auto-generated method stub
		
		return this.RESULT;
	}

	@Override
	public void setResult(int result) 
	{
		// TODO Auto-generated method stub
		this.RESULT = result;
	}

	@Override
	public boolean equals(Player player) 
	{
		checkPlayer(player);
		return (id.equals(player.getPlayerId()));
	}
	
	public boolean equals(Object player)
	{
		if(player == null)
			throw new IllegalArgumentException("null obj");
		if (player instanceof Player)
		{
			Player currPlayer = (Player) player;
			return (id.equals(currPlayer.getPlayerId()));
		}
		return false;
	}
	
	public int hashCode()
	{
		//generate hashCode based on playerID.
		return id.hashCode();
	}

	@Override
	public int compareTo(Player player) 
	{
		checkPlayer(player);
		return id.compareTo(player.getPlayerId());
	}
	
	public String toString()
	{
		return String.format(
				"Player: id=%s, name=%s, bet=%d, points=%d, RESULT .. %d",
				this.id, this.playerName, this.bet, this.points, this.RESULT);
	}
	/**
	 * <pre> checks to see if the player is null
	 * @param player
	 *            the player in question
	 * </pre>
	 */
	private void checkPlayer(Player player)
	{
		if(player == null)
			throw new IllegalArgumentException("null obj");
	}
}
