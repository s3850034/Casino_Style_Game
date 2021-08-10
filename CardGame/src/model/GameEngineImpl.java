package model;

import java.util.Collection;
import java.util.Deque;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Collections;
import java.util.TreeMap;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine 
{
	//JCF and Maps used for storing players, a deck and callback
	private TreeMap<String,Player> players = new TreeMap<String,Player>();
	private Deque<PlayingCard> deck = getShuffledHalfDeck();
	private Collection<GameEngineCallback> callbacks = new ArrayList<GameEngineCallback>();
	//These integers are important for calling the appropriate callback methods to all call backs
	final private int NEXT_CARD = 0;
	final private int BUST_CARD = 1;
	final private int RESULT = 2;
	final private int HOUSE_NEXT_CARD = 3;
	final private int HOUSE_BUST_CARD = 4;
	final private int HOUSE_RESULT = 5;
	
	@Override
	public void dealPlayer(Player player, int delay) throws IllegalArgumentException
	{
		// TODO Auto-generated method stub
		checkPlayer(player); //This method checks to see if the player is null or is within the collection
		delayChecker(delay); //Checks to see if the delay numbers provided are okay.
		int current_score = 0;
        while(current_score <= BUST_LEVEL)
        {
        	current_score = resultIncrement(current_score); //increments the score based on the top card of the deck and then creates the delay
        	if(current_score > BUST_LEVEL)
        	{
        		current_score -= deck.getFirst().getScore(); //This if statement will check if current score is okay to bust.
        		break;
        	}
        	playerCallbacksMethodCaller(player,NEXT_CARD); //this method calls the nextCard method for all call backs
        	delayMechanism(delay); 
        	deck.remove(); 	
        }
        playerCallbacksMethodCaller(player,BUST_CARD); //calls the bustCard method for all call backs
        deck.remove();
        player.setResult(current_score); //sets the score into the result
        playerCallbacksMethodCaller(player,RESULT); //calls the result method for all call backs
	}

	@Override
	public void dealHouse(int delay) throws IllegalArgumentException 
	{
		// TODO Auto-generated method stub
		delayChecker(delay);
		int current_score = 0;
		current_score = dealerHouse(delay); //Same loop as above but however, calls the nextHouseCard method for all call backs
		houseCallbacksMethodCaller(current_score, HOUSE_BUST_CARD); //calls houseBustCard when loop ends, for all call backs
        deck.remove();
        for(Player player: getAllPlayers()) //applies the win/loss condition based on the players participating and their results
        	applyWinLoss(player,current_score);
        houseCallbacksMethodCaller(current_score, HOUSE_RESULT); //calls houseResult
        for(Player player: getAllPlayers()) //resets the bet for all players in prep for next round
        	player.resetBet();
        shuffleDeckAgain(); //shuffles deck in prep for next round
	}

	@Override
	public void applyWinLoss(Player player, int houseResult) 
	{
		// TODO Auto-generated method stub
		if (player.getResult() > houseResult)
			player.setPoints(player.getPoints() + player.getBet());
		else if (player.getResult() < houseResult)
			player.setPoints(player.getPoints() - player.getBet());
		
	}

	@Override
	public void addPlayer(Player player) 
	{
		// TODO Auto-generated method stub
		if (players.containsKey(player.getPlayerId()))
			players.replace(player.getPlayerId(), player);
		players.put(player.getPlayerId(), player);
						
	}

	@Override
	public Player getPlayer(String id) 
	{
		// TODO Auto-generated method stub
		if (id == null) //checks to see if id real
			return null;
		for (Player player: players.values())
		{
			if(id.equals(player.getPlayerId()))
				return player;
		}	
		return null;
	}

	@Override
	public boolean removePlayer(Player player) 
	{
		// TODO Auto-generated method stub
		if (player == null)
			throw new IllegalArgumentException();
		if(players.containsKey(player.getPlayerId()))
		{
			players.remove(player.getPlayerId());
			return true;
		}
		return false;
	}

	@Override
	public boolean placeBet(Player player, int bet) 
	{
		// TODO Auto-generated method stub
		checkPlayer(player);
		if(player.setBet(bet))
			return true;
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) 
	{
		// TODO Auto-generated method stub
		if (gameEngineCallback == null)
			throw new IllegalArgumentException("null args");
		callbacks.add(gameEngineCallback);
		
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) 
	{
		// TODO Auto-generated method stub
		if (gameEngineCallback == null)
			throw new IllegalArgumentException();
		if (callbacks.contains(gameEngineCallback))
		{
			callbacks.remove(gameEngineCallback);
		    return true;
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() 
	{
		// TODO Auto-generated method stub
		//TreeSet automatically sorts the id in natural ordering
		Collection<Player> players_copy = new TreeSet<Player>();
		players_copy = Collections.unmodifiableCollection(players.values());
		return players_copy;
	}

	@Override
	public Deque<PlayingCard> getShuffledHalfDeck() 
	{
		// TODO Auto-generated method stub
		LinkedList<PlayingCard> deck_copy = new LinkedList<PlayingCard>();
		for (Suit suit: Suit.values())
		{
			for (Value value: Value.values())
			{
				PlayingCard card = new PlayingCardImpl(suit,value);
				deck_copy.add(card);
			}
		}
		Collections.shuffle(deck_copy);
		return deck_copy;
	}
	/**
	 * <pre> called to shuffle deck again
	 * </pre>
	 */
	private void shuffleDeckAgain()
	{
		deck = getShuffledHalfDeck();
	}
	/**
	 * <pre> called to create a new shuffled deck when deck is empty
	 * </pre>
	 */
	private void emptyDeckShuffler()
	{
		if(deck.isEmpty())
			shuffleDeckAgain();
	}
	/**
	 * <pre> performs the delay for the card deals
	 * @param delay
	 *           the duration of delay
	 * </pre>
	 */
	private void delayMechanism(int delay)
	{
    	try 
    	{
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * <pre> checks to see the if the bounds for the delay are correct
	 * @param delay
	 * 			 the duration of delay
	 * </pre>
	 */
	private void delayChecker(int delay)
	{
		final int BOUNDS1 = 0;
		final int BOUNDS2 = 1000;
		if(delay < BOUNDS1 || delay > BOUNDS2)
			throw new IllegalArgumentException();
	}
	/**
	 * <pre> calls the appropriate callback method based on the number provided for all call backs
	 * @param player
	 *            the player that is passed to the call backs so they can be called
	 * @param method_num
	 *            the integer that corresponds to the method being called
	 * </pre>
	 */
	private void playerCallbacksMethodCaller(Player player, int method_num)
	{	
		if(method_num == NEXT_CARD)
		{
			for(GameEngineCallback callback: callbacks)
        		callback.nextCard(player, deck.getFirst(), this);
		}
		else if(method_num == BUST_CARD)
		{
		    for(GameEngineCallback callback: callbacks)
		        callback.bustCard(player, deck.getFirst(), this);
		}
		else if(method_num == RESULT)
		{
	        for(GameEngineCallback callback: callbacks)
	        	callback.result(player,player.getResult(), this);
		}
	}
	/**
	 * <pre> calls the appropriate callback method based on the number provided for all call backs
	 * @param score
	 *            the final result attained by the House
	 * @param method_num
	 *            the integer that corresponds to the method being called
	 * </pre>
	 */
	private void houseCallbacksMethodCaller(int score, int method_num)
	{
		if(method_num == HOUSE_NEXT_CARD)
		{
	        for(GameEngineCallback callback: callbacks)
	        	callback.nextHouseCard(deck.getFirst(), this);
		}
		else if(method_num == HOUSE_BUST_CARD)
		{
	        for(GameEngineCallback callback: callbacks)
	        	callback.houseBustCard(deck.getFirst(), this);
		}
		else if(method_num == HOUSE_RESULT)
		{
	        for(GameEngineCallback callback: callbacks)
	        	callback.houseResult(score,this);
		}
	}
	/**
	 * <pre> deals the Houses hand
	 * @param delay
	 *           the duration of delay 
	 * @return the result of the House's hand
	 * </pre>
	 */
	private int dealerHouse(int delay)
	{
		int current_score = 0;
        while(current_score <= BUST_LEVEL)
        {
        	current_score = resultIncrement(current_score);
        	if(current_score > BUST_LEVEL)
        	{
        		current_score -= deck.getFirst().getScore();
        		break;
        	}
        	houseCallbacksMethodCaller(current_score,HOUSE_NEXT_CARD);
        	delayMechanism(delay);
        	deck.remove(); 	
        }
        return current_score;
	}
	/**
	 * <pre> increments the result based on the first card of the deck
	 * @param score
	 *           the current score of the hand 
	 * @return the current score after incrementing
	 * </pre>
	 */
	private int resultIncrement(int score)
	{
    	emptyDeckShuffler();
    	score += deck.getFirst().getScore();
    	return score;
	}
	/**
	 * <pre> checks to see if the player is either null or if it exists in the collection
	 * @param player
	 *            the current player
	 * </pre>
	 */
	private void checkPlayer(Player player)
	{
		if (player == null || players.containsValue(player) == false)
			throw new IllegalArgumentException();
	}
	
}