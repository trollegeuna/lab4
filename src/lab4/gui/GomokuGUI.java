package lab4.gui;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import lab4.client.GomokuClient;
import lab4.data.GameGrid;
import lab4.data.GomokuGameState;

/*
 * The GUI class
 */

public class GomokuGUI implements Observer{
	private GamePanel gameGridPanel;
	private JButton connectButton, newGameButton, disconnectButton;
	private JLabel messageLabel;
	
	private GomokuClient client;
	private GomokuGameState gamestate;
	
	/**
	 * The constructor
	 * 
	 * @param g   The game state that the GUI will visualize
	 * @param c   The client that is responsible for the communication
	 */
	public GomokuGUI(GomokuGameState g, GomokuClient c){
		//Set up window
		JFrame window = new JFrame("Gomoku");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout layout = new SpringLayout();
		
		Container contentPane = window.getContentPane();
		contentPane.setLayout(layout);
		
		//----Add components----
		//GamePanel TODO uncomment when implemented
//		gameGridPanel = new GamePanel(g.getGameGrid());//It is actually a JPanel
//		window.add(gameGridPanel);
//		layout.putConstraint(SpringLayout.NORTH, gameGridPanel, 5, SpringLayout.NORTH, contentPane);//Set top to 5 px from top
//		layout.putConstraint(SpringLayout.WEST, gameGridPanel, 5, SpringLayout.WEST, contentPane);//Set left 5 px from windows left
//		
		//---Buttons---
		//Connect button
		connectButton = new JButton("Connect");
		connectButton.addActionListener(new ActionListener() {//Should be anonymous
			public void actionPerformed(ActionEvent e)
			{
				//Connect
				JOptionPane.showMessageDialog(null, "Connecting... (Not Yet Implemented)");
			}
		});
		window.add(connectButton);
		layout.putConstraint(SpringLayout.NORTH, connectButton, 5, SpringLayout.SOUTH, window/*gameGridPanel*/);//Set top to game grid's bottom + 5 px padding
		layout.putConstraint(SpringLayout.WEST, connectButton, 5, SpringLayout.WEST, window);
		
		//New game button
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//Set up new game
				JOptionPane.showMessageDialog(null, "Setting up new game... (Not Yet Implemented)");
			}
		});
		window.add(newGameButton);
		layout.putConstraint(SpringLayout.NORTH, newGameButton, 5, SpringLayout.SOUTH, window/*gameGridPanel*/);
		layout.putConstraint(SpringLayout.WEST, newGameButton, 5, SpringLayout.EAST, connectButton);
		
		//Disconnect button
		disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//Disconnect
				JOptionPane.showMessageDialog(null, "Disconnecting... (Not Yet Implemented)");
			}
		});
		window.add(disconnectButton);
		layout.putConstraint(SpringLayout.NORTH, disconnectButton, 5, SpringLayout.SOUTH, window/*gameGridPanel*/);
		layout.putConstraint(SpringLayout.WEST, disconnectButton, 5, SpringLayout.EAST, newGameButton);
		
		//Label
		messageLabel = new JLabel("Welcome to Gomoku!");
		window.add(messageLabel);
		layout.putConstraint(SpringLayout.NORTH, messageLabel, 5, SpringLayout.SOUTH, connectButton);
		layout.putConstraint(SpringLayout.WEST, messageLabel, 5, SpringLayout.WEST, window);
		
		//Packs content (which sets window size) and displays window
		window.pack();
		window.setVisible(true);
		
		//Set up this as observer to specified observables
		//TODO: Enable when implemented
		/*this.client = c;
		this.gamestate = g;
		client.addObserver(this);
		gamestate.addObserver(this);*/
	}
	
	
	public void update(Observable arg0, Object arg1) {
		
		// Update the buttons if the connection status has changed
		if(arg0 == client){
			if(client.getConnectionStatus() == GomokuClient.UNCONNECTED){
				connectButton.setEnabled(true);
				newGameButton.setEnabled(false);
				disconnectButton.setEnabled(false);
			}else{
				connectButton.setEnabled(false);
				newGameButton.setEnabled(true);
				disconnectButton.setEnabled(true);
			}
		}
		
		// Update the status text if the gamestate has changed
		if(arg0 == gamestate){
			messageLabel.setText(gamestate.getMessageString());
		}
		
	}
	
}