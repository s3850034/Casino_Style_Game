package view;

import javax.swing.JOptionPane;

//This class is the main dialog box that tells the player what is wrong with their inputs

public class AlertingBox extends JOptionPane
{
	public void showAlert(String title,String alert)
	{
		JOptionPane.showMessageDialog(null,alert,title,JOptionPane.WARNING_MESSAGE);
	}
}
