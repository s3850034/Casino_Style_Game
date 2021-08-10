package view;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.interfaces.Player;
import view.AlertingBox;
import view.model.InputChecker;

//The purpose of this class is to let the user enter in the bid value for player
public class BetBox extends JOptionPane
{
	private String bettingAmount;
	private PlayersComboBox combo;
	
	public BetBox(PlayersComboBox combo)
	{	
		this.combo = combo;
		enterBettingDetails();
	}
	
	public void enterBettingDetails()
	{
		 AlertingBox alert = new AlertingBox();
		 InputChecker checker = new InputChecker();
		 
		 boolean flag = false;
		 
		 while(!flag)
		 {
			 String betAmount = showInputDialog(null,"Enter Bet Amount(enter positive integer):","Betting Details",JOptionPane.INFORMATION_MESSAGE);
			 if(betAmount == null)
				 flag = true;
			 else if(!checker.isPositiveInteger(betAmount))
			 {
			     alert.showAlert("Invalid Input","Make sure you are entering the correct input");
			 } 
			 else if(checker.isBetHigher(betAmount, (Player) combo.getSelectedItem()))
			 {
				 alert.showAlert("Invalid Input", "Make sure the bet does not exceed the player's balance");
			 }
			 else
			 {
				 bettingAmount = betAmount;
				 flag = true;
			 }
		 }
	}
	
	public String getBettingAmount()
	{
		if (bettingAmount == null)
			return null;
		return bettingAmount;
	}
	
	
}
