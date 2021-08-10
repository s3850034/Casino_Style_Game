package view.model;

import java.util.ArrayList;
//this class was specifically created for the purposes of making sure no duplicate players with the same ID were in the game
public class IDTracker 
{
	private ArrayList<Integer> idTracker;
	
	public IDTracker()
	{
		idTracker = new ArrayList<Integer>();
	}
	
	public void addIdToTrack(int id)
	{
		idTracker.add(id);
	}
	
	public void removeIdToTrack(int id)
	{
		for(int x = 0; x < idTracker.size(); x++)
		{
			if(idTracker.get(x) == id)
				idTracker.remove(x);
		}
	}
	
	public ArrayList<Integer> getIDTracker()
	{
		return idTracker;
	}
	
	public boolean containsID(int ID)
	{
		for(int x = 0; x < idTracker.size(); x++)
		{
			if(idTracker.get(x) == ID)
				return true;
		}
		return false;
	}
}
