package controller;

import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import view.CardGameFrame;
import view.CardPanel;

//the purpose of this class is to handle the panel resize of CardPanel

public class PanelResizeController implements ComponentListener
{
	private Graphics2D g;
	private CardPanel panel;
	
	public PanelResizeController(CardPanel panel)
	{
		this.panel = panel;
	}
	@Override
	public void componentResized(ComponentEvent e) 
	{
      int width = panel.getWidth();
      int height = panel.getHeight();
      
    }
	@Override
	public void componentHidden(ComponentEvent e) 
	{
		//don't need to implement
		
	}
	@Override
	public void componentMoved(ComponentEvent e) 
	{
		//don't need to implement
		
	}
	@Override
	public void componentShown(ComponentEvent e) 
	{
		//don't need to implement
		
	}

}
