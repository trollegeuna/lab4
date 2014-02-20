package lab4;

import lab4.client.*;
import lab4.data.GomokuGameState;
import lab4.gui.GomokuGUI;

public class GomokuMain {

	public static void main(String[] args) {
		GomokuClient client = new GomokuClient(Integer.parseInt(args[0]));
		GomokuGameState gameState = new GomokuGameState(client);
		GomokuGUI gui = new GomokuGUI(gameState, client);
	}

}
