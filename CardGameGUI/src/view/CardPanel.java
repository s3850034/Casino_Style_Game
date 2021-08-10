package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.PanelResizeController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

//this class contains the panel that stores the cards that were dealed to for each player for a particular round

public class CardPanel extends JPanel
{
	private HashMap<PlayingCard,Player> playersHand;
	private HashMap<PlayingCard,Player> bustedHand;
	private PlayersComboBox combo;
	private CardGameFrame frame;
	private CardCreator cardCreated;
	private int panelWidth;
	private int panelHeight;
	public CardPanel(CardGameFrame frame, Dimension dimension) throws IOException
	{
		this.frame = frame;
		setPreferredSize(new Dimension((int) dimension.getWidth(),(int) (dimension.getHeight()*7)/4));
		setBorder(BorderFactory.createTitledBorder("Card Panel"));
		playersHand = new HashMap<PlayingCard,Player>();
		bustedHand = new HashMap<PlayingCard,Player>();
		PanelResizeController controller = new PanelResizeController(this);
		addComponentListener(controller);
		cardCreated = new CardCreator(this);
		
	}
	
	public void signal(PlayersComboBox combo)
	{
		this.combo = combo;
	}
	
	public void playerUpdate(Player player, PlayingCard card)
	{
		playersHand.put(card,player);
	}
	
	public void playerBustUpdate(Player player, PlayingCard card)
	{
		bustedHand.put(card,player);
	}
	
	public void houseUpdate(PlayingCard card)
	{
		playersHand.put(card, combo.getHouse());
	}
	
	public void houseBustUpdate(PlayingCard card)
	{
		bustedHand.put(card,combo.getHouse());
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(!playersHand.isEmpty())
		{
			for(Player value: playersHand.values())
			{
				if(value.equals((Player) combo.getSelectedItem()))
				{
					drawHand(g,value);
					g.setColor(Color.BLACK);
				}
		
			}
			repaint();
		}

		
	}
	

	public void drawHand(Graphics g,Player player)
	{
		boolean suitsDrawn = false;
		int x_coordinate = (int) (getWidth() * 0.05);
		int y_coordinate = (int) (getHeight() * 0.15);
		
		ArrayList<PlayingCard> toIterate = getAllCards(playersHand, (Player) combo.getSelectedItem());
		ArrayList<PlayingCard> busted = getAllCards(bustedHand, (Player) combo.getSelectedItem());
		
		for (PlayingCard card : toIterate)
		{
			g.setColor(Color.WHITE);
			cardCreated.cardCreate(g, x_coordinate, y_coordinate, card);
			x_coordinate += (int) (getWidth() * 0.15);
		}
		
		if (player.getResult() != 42)
		{
			for(PlayingCard bustedCard: busted)		
			{
				g.setColor(Color.LIGHT_GRAY);
				cardCreated.cardCreate(g, x_coordinate, y_coordinate, bustedCard);
			}
		}

	}
	
	public ArrayList<PlayingCard> getAllCards(HashMap<PlayingCard,Player> cardForPlayer,Player currPlayer)
	{
		ArrayList<PlayingCard> returningCards = new ArrayList<PlayingCard>();
		
		for(HashMap.Entry<PlayingCard,Player> player : cardForPlayer.entrySet())
		{
			if(player.getValue().equals(currPlayer))
				returningCards.add(player.getKey());
		}
		
		return returningCards;
	}
	
	public void haveStartedAgain()
	{
		playersHand.clear();
		bustedHand.clear();
	}
	
}
