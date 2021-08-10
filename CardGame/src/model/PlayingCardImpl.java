package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard 
{
	private Suit suit;
	private Value value;
	private int score;
	
	public PlayingCardImpl(Suit suit, Value value)
	{
		if (suit == null || value == null)
			throw new IllegalArgumentException("null args");
		this.suit = suit;
		this.value = value;
	}
	
	@Override
	public Suit getSuit() 
	{
		return this.suit;
	}

	@Override
	public Value getValue() 
	{
		return this.value;
	}

	@Override
	public int getScore() 
	{
		// TODO Auto-generated method stub
		switch(this.value)
		{
		case EIGHT: 
			score = 8;
			break;
		case NINE: 
			score = 9;
			break;
		case TEN: 
			score = 10;
			break;
		case JACK: 
			score = 10;
			break;
		case QUEEN: 
			score = 10;
			break;
		case KING: 
			score = 10;
			break;
		case ACE: 
			score = 11;
			break;
		}
		return score;
	}
	
	public String toString()
	{
		return "Suit: " + suitString(this.suit) + ", Value: "+ valueString(this.value) + ", Score: " + String.valueOf(getScore());
	}

	@Override
	public boolean equals(PlayingCard card) 
	{
			checkCard(card);
			return (suit.equals(card.getSuit()) && value.equals(card.getValue()));
	}
	
	public boolean equals(Object card)
	{
		if(card == null)
			return false;
		if (card instanceof PlayingCard)
		{
			PlayingCard currCard = (PlayingCard) card;
			return (suit.equals(currCard.getSuit()) && value.equals(currCard.getValue()));
		}
		return false;
	}
	
	public int hashCode()
	{
		return suit.hashCode() + value.hashCode();
	}
	/**
	 * <pre> converts the value into an eligible string
	 * @param string
	 * 			  the Value that will be turned into a string
	 * @return string version of the value
	 * </pre>
	 */
	private String valueString(Value string)
	{
		//EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
		String valueS = "";
		if (string.equals(Value.EIGHT))
			valueS = "Eight";
		else if(string.equals(Value.NINE))
			valueS = "Nine";
		else if(string.equals(Value.TEN))
			valueS = "Ten";
		else if(string.equals(Value.JACK))
			valueS = "Jack";
		else if(string.equals(Value.QUEEN))
			valueS = "Queen";
		else if(string.equals(Value.KING))
			valueS = "King";
		else if(string.equals(Value.ACE))
			valueS = "Ace";
		return valueS;
	}
	/**
	 * <pre> converts the suit into an eligible string
	 * @param string
	 * 			  the Suit that will be turned into a string
	 * @return string version of the suit
	 * </pre>
	 */
	private String suitString(Suit string)
	{
		//HEARTS, SPADES, CLUBS, DIAMONDS
		String suitS = "";
		if (string.equals(Suit.HEARTS))
			suitS = "Hearts";
		else if(string.equals(Suit.SPADES))
			suitS = "Spades";
		else if(string.equals(Suit.CLUBS))
			suitS = "Clubs";
		else if(string.equals(Suit.DIAMONDS))
			suitS = "Diamonds";
		return suitS;
	}
	/**
	 * <pre> checks to see if the card is null 
	 * @param card
	 *          card in question
	 */
	private void checkCard(PlayingCard card)
	{
		if(card == null)
			throw new IllegalArgumentException("null obj");
	}

}

	