package view.model;

import model.interfaces.Player;

//This class checks the particular inputs that a player could give and tries to prevent any errors
public class InputChecker 
{
	public boolean isInteger(String getText)
	{
		if(getText == null || getText.equals(""))
			return false;
		try
		{
			int temporaryInteger = Integer.parseInt(getText);
			
		} catch(NumberFormatException nfe)
		{
			return false;
		}
		return true;
	}
	
	public boolean isPositiveInteger(String getText)
	{
		int temporaryInteger;
		if(getText == null || getText.equals(""))
			return false;
		try
		{
			temporaryInteger = Integer.parseInt(getText);
			
		} catch(NumberFormatException nfe)
		{
			return false;
		}
		if(temporaryInteger > 0)
			return true;
		return false;

	}
	
	public boolean isBetHigher(String getText,Player player)
	{
		int temporaryInteger = Integer.parseInt(getText);
		if (temporaryInteger > player.getPoints())
			return true;
		else
			return false;
	}
	
	
	
	

}
