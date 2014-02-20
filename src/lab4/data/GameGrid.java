package lab4.data;

import java.util.Observable;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**
 * Represents the 2-d game grid
 */

public class GameGrid extends Observable{
	/*
	 * added ints and a 2 dim array and a int for counting rows
	 */
	public static final int EMPTY = 0;
	public static final int ME = 1;
	public static final int OTHER = 2;
	public static final int INROW = 5;
	private int[][] grid;

	
	/**
	 * Constructor
	 * 
	 * @param size The width/height of the game grid
	 */
	public GameGrid(int size){
		grid = new int [size][size];
	}
	
	/**
	 * Reads a location of the grid
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return the value of the specified location
	 */
	public int getLocation(int x, int y){
		return grid[x][y];
	}
	
	/**
	 * Returns the size of the grid
	 * 
	 * @return the grid size
	 */
	public int getSize(){
		return grid.length;
	}
	
	/**
	 * Enters a move in the game grid
	 * 
	 * @param x the x position
	 * @param y the y position
	 * @param player
	 * @return true if the insertion worked, false otherwise
	 */
	public boolean move(int x, int y, int player){
		if (grid[x][y] == EMPTY) {
			grid[x][y] = player;
			setChanged();
			notifyObservers();
			return true;
		} else {
			setChanged();
			notifyObservers();
			return false;

		}
	}
	
	/**
	 * Clears the grid of pieces
	 */
	public void clearGrid(){
		int NUM = getSize();
		for (int i = 0; i < NUM; i++) {
			for (int j = 0; j < NUM; j++) {
				grid[i][j] = EMPTY;
			}
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Check if a player has 5 in row
	 * 
	 * @param player the player to check for
	 * @return true if player has 5 in row, false otherwise
	 */
	public boolean isWinner(int player){
		if (rowCheck(player)) {
			return true;
		}else if (colCheck(player)) {
			return true;
		}else if (forwardDiagCheck(player)) {
			return true;
		}else if (backwardDiagCheck(player)) {
			return true;
		}else {
			return false;
		}
		
	}
	private boolean rowCheck(int player) {
		
		for (int x = 0; x < getSize(); x++) {
			for (int y = 0; y < getSize(); y++) {
				int counter = 0;
				int i = x;
				while (i < getSize() && i < getSize() && grid[i][y] == player) {
					counter++;
					i++;
				}
				if (counter >= INROW) {
					return true;
				}
			}
		}
		return false;	
		
	}
	
	private boolean colCheck(int player) {
		
		for (int x = 0; x < getSize(); x++) {
			
			for (int y = 0; y < getSize(); y++) {
				int counter = 0;
				int j = y;
				while (j < getSize() && j < getSize() && grid[x][j] == player) {
					counter++;
					j++;
				}
				if (counter >= INROW) {
					return true;
				}
			}
		}
		return false;	
	}
	
	private boolean forwardDiagCheck(int player) {
	
			
		for (int x = 0; x < getSize(); x++) {
			for(int y = 0; y < getSize(); y++){
				
				int counter = 0;
				int i = x;
				int j = y;
				while (i < getSize() && j < getSize() && grid[i][j] == player) {
					counter++;
					i++;
					j++;
				}
				if (counter >= INROW) {
					return true;
				} 
			}
		}
		return false;
		
	}
	
	private boolean backwardDiagCheck(int player) {
		
		for (int x = 0; x < getSize(); x++) {
			for(int y = 0; y < getSize(); y++){
				
				int counter = 0;
				int i = x;
				int j = y;
				
				while (i < getSize() && j < getSize() && i >= 0 && grid[i][j] == player) {
					counter++;
					i--;
					j++;
				}
				if (counter >= INROW) {
					return true;
				} 
			}
		}
		return false;
		
	}
	
	
}