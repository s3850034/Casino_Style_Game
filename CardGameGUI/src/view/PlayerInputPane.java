package view;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import view.AlertingBox;
import view.model.InputChecker;

//this class lets the user essentially give input for the add player button
public class PlayerInputPane extends JOptionPane
{
	private String playerInputs[] =  new String[3];
	private String playerId;
	private String playerName;
	private String playerBalance;
	public PlayerInputPane()
	{
		 AlertingBox alert = new AlertingBox();
		 InputChecker checker = new InputChecker();
		 
		 boolean flag = false;
		 
		 while(!flag)
		 {
			 playerId = showInputDialog(null,"Enter Player ID(enter positive integer):","Player ID Details",JOptionPane.INFORMATION_MESSAGE);
			 if(playerId == null)
				 flag = true;
			 else if(!checker.isPositiveInteger(playerId))
			 {
			     alert.showAlert("Invalid Input","Make sure you are entering the correct input");
			 } 
			 else
			 {
				 playerInputs[0] = playerId;
				 flag = true;
			 }
		 }
		 if(playerId != null)
			 flag = false;
		 
		 while (!flag)
		 {
			 playerName = showInputDialog(null,"Enter Player Name(enter anything):","Player Name Details",JOptionPane.INFORMATION_MESSAGE);
			 if (playerName == null)
				 flag = true;
			 else
			 {
				 playerInputs[1] = playerName;
				 flag = true;
			 }
				 
		 }
		 
		 if(playerName != null)
			 flag = false;
		 
		 while(!flag)
		 {
			 playerBalance = showInputDialog(null,"Enter Player Balance (integer):","Player ID Details",JOptionPane.INFORMATION_MESSAGE);
			 if(playerBalance == null)
				 flag = true;
			 else if(!checker.isPositiveInteger(playerBalance))
			 {
			     alert.showAlert("Invalid Input","Make sure you are entering the correct input");
			 } 
			 else
			 {
				 playerInputs[2] = playerBalance;
				 flag = true;
			 }
		 }
	}
	
	public String[] getRow()
	{   for (int x = 0; x < playerInputs.length; x++ )
	    {
			if(playerInputs[x] == null)
				return null;
	    }
	
		return playerInputs;
	}

}
