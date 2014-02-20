package lab4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lab4.data.GameGrid;

/**
 * A panel providing a graphical view of the game board
 */

public class GamePanel extends JPanel implements Observer{

	private final int UNIT_SIZE = 20;
	private GameGrid grid;
	
	/**
	 * The constructor
	 * 
	 * @param grid The grid that is to be displayed
	 */
	public GamePanel(GameGrid grid){
		this.grid = grid;
		grid.addObserver(this);
		Dimension d = new Dimension(grid.getSize()*UNIT_SIZE+1, grid.getSize()*UNIT_SIZE+1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
	}

	/**
	 * Returns a grid position given pixel coordinates
	 * of the panel
	 * 
	 * @param x the x coordinates
	 * @param y the y coordinates
	 * @return an integer array containing the [x, y] grid position
	 */
	public int[] getGridPosition(int x, int y){
		int[] coordinates = new int[2];
		coordinates[0] = (x / UNIT_SIZE);//Get x-component
		coordinates[1] = (y / UNIT_SIZE);//Get y-component
		return coordinates;
	}
	
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int GridSize = grid.getSize();
		
		//BG
		g.setColor(Color.white);
		g.fillRect(0, 0, UNIT_SIZE * GridSize, UNIT_SIZE * GridSize);
		
		//Boxes
		g.setColor(Color.black);
		for(int row = 0; row < GridSize; row++)
		{
			for(int col = 0; col < GridSize; col++)
			{
				g.drawRect(UNIT_SIZE * col, UNIT_SIZE * row, UNIT_SIZE, UNIT_SIZE);
				
				switch(grid.getLocation(col, row))
				{
					case GameGrid.ME:
						g.drawLine(UNIT_SIZE * col, UNIT_SIZE * row, UNIT_SIZE * (col + 1), UNIT_SIZE * (row + 1));
						g.drawLine(UNIT_SIZE * (col + 1), UNIT_SIZE * row, UNIT_SIZE * col, UNIT_SIZE * (row + 1));
						break;
					
					case GameGrid.OTHER:
						g.drawOval(UNIT_SIZE * col, UNIT_SIZE * row, UNIT_SIZE, UNIT_SIZE);
						break;
				}
			}
		}
	}
	
}