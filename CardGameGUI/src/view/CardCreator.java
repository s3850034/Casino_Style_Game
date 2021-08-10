package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

import model.interfaces.PlayingCard;

//This class draws each card for the card panel
public class CardCreator 
{
	private ImageIcon clubs = new ImageIcon(".\\suitImages\\clubs.png");
	private ImageIcon hearts = new ImageIcon(".\\suitImages\\hearts.png");
	private ImageIcon diamonds = new ImageIcon(".\\suitImages\\diamonds.png");
	private ImageIcon spades = new ImageIcon(".\\suitImages\\spades.png");
	
	private Image clubsResizeable;
	private Image heartsResizeable;
	private Image diamondsResizeable;
	private Image spadesResizeable;
	private CardPanel panel;
	
	public CardCreator(CardPanel panel)
	{
		this.panel = panel;
		clubsResizeable = clubs.getImage();
		heartsResizeable = hearts.getImage();
		diamondsResizeable = diamonds.getImage();
		spadesResizeable = spades.getImage();
			
		clubsResizeable.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		heartsResizeable.getScaledInstance(40,40, Image.SCALE_SMOOTH);
		diamondsResizeable.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		spadesResizeable.getScaledInstance(40,40, Image.SCALE_SMOOTH);
	    
	}
	
	public void cardCreate(Graphics g, int x, int y, PlayingCard card) 
	{
		Graphics2D tempG = (Graphics2D) g;
		tempG.fillRoundRect(x,y,(int) (panel.getWidth() * 0.12),(int) (panel.getHeight() * 0.60),(int) (panel.getWidth() * 0.01),(int) (panel.getHeight() * 0.01)); //Here the 1.5W = H - Width to Height ratio is maintained
		drawingSuit(tempG,x,y,card);
		drawingLabels(tempG,x,y,card);
	}
	
	
	
	public void drawingSuit(Graphics2D g, int x, int y, PlayingCard card)
	{
 
		int x_coordinate = x + ((int) ((panel.getWidth()*0.05)));
		int y_coordinate = y + ((int) ((panel.getHeight()*0.23)));
				
		if(card.getSuit() == PlayingCard.Suit.HEARTS)
		{
			g.drawImage(heartsResizeable,x_coordinate,y_coordinate,(int) (panel.getWidth() * 0.03),(int) (panel.getHeight() * 0.10), null);
		}
		if(card.getSuit() == PlayingCard.Suit.DIAMONDS)
		{
			g.drawImage(diamondsResizeable, x_coordinate,y_coordinate,(int) (panel.getWidth() * 0.03),(int) (panel.getHeight() * 0.10), null);
		}
			
		if(card.getSuit() == PlayingCard.Suit.SPADES)
		{
			g.drawImage(spadesResizeable, x_coordinate,y_coordinate,(int) (panel.getWidth() * 0.03),(int) (panel.getHeight() * 0.10), null);
		}
			
		if(card.getSuit() == PlayingCard.Suit.CLUBS)
		{
			g.drawImage(clubsResizeable, x_coordinate,y_coordinate, (int) (panel.getWidth() * 0.03),(int) (panel.getHeight() * 0.10),null);
		}
			
	}
	
	public void drawingLabels(Graphics2D g, int x, int y, PlayingCard card)
	{
		int fontSize = (int) (panel.getWidth() * 0.02);
		g.setColor(whichColor(card));
		g.setFont(new Font("SansSerif",Font.BOLD,fontSize));
		String valueD = whichValue(card);
		g.drawString(valueD, x+((int) ((panel.getWidth()*0.01))), y+((int) ((panel.getHeight()*0.085))));
		g.drawString(valueD, x+((int) ((panel.getWidth()*0.093))), y+((int) ((panel.getHeight()*0.58))));
		
	}
	
	public Color whichColor(PlayingCard card)
	{
		if(card.getSuit() == PlayingCard.Suit.HEARTS || card.getSuit() == PlayingCard.Suit.DIAMONDS)
			return Color.RED;
		else
			return Color.BLACK;
	}
	
	public String whichValue(PlayingCard card)
	{
		if(card.getValue() == PlayingCard.Value.ACE)
		{
			return "A";
			
		}
		else if(card.getValue() == PlayingCard.Value.EIGHT)
		{
			return "8";
			
		}
		else if(card.getValue() == PlayingCard.Value.NINE)
		{
			return "9";
			
		}
		else if(card.getValue() == PlayingCard.Value.TEN)
		{
			return "10";
			
		}
		else if(card.getValue() == PlayingCard.Value.JACK)
		{
			return "J";
			
		}
		else if(card.getValue() == PlayingCard.Value.QUEEN)
		{
			return "Q";
			
		}
		else
		{
			return "K";
			
		}
	}
		
}
