package lab4;

import lab4.client.*;
import lab4.data.GomokuGameState;
import lab4.gui.GomokuGUI;

public class GomokuMain {

	public static void main(String[] args) {
		GomokuClient client;
		if(args.length == 1)
		{
			client = new GomokuClient(Integer.parseInt(args[0]));
		}
		else
		{
			client = new GomokuClient(7000);
		}
		GomokuGameState gameState = new GomokuGameState(client);
		GomokuGUI gui = new GomokuGUI(gameState, client);
	}

}
