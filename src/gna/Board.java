package gna;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {

	private int[][] tiles;

	// construct a board from an N-by-N array of tiles
	// TODO (make a deep copy of tiles and store it into this.tiles)
	public Board(int[][] tiles) {
		this.setTiles(this.getDeepCopy(tiles));
	}

	// return number of blocks out of place
	public int hamming() {
		int expectedValue = 1;
		int nbrIncorrect = 0;
		for (int row = 0; row < this.getSize(); row++) {
			for (int column = 0; column < this.getSize(); column++) {
				int val = this.getValue(row, column);
				if(val != 0 && val != expectedValue) {
					nbrIncorrect++;
				}
				expectedValue++;
			}
		}
		return nbrIncorrect;
	}

	// return sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int totalDistance = 0;
		for (int row = 0; row < this.getSize(); row++) {
			for (int column = 0; column < this.getSize(); column++) {
				int val = this.getValue(row, column);
				if(val != 0 ) {
					totalDistance += this.getDistance(row, column, val);
				}
			}
		}
		return totalDistance;
	}
	
	private int getDistance(int row, int column, int value) {	
		int[] pos = this.getXYPosition(value);
		return Math.abs(row - pos[0]) + Math.abs(column - pos[1]);
	}

	// Does this board equal y. Two boards are equal when they both were constructed
	// using tiles[][] arrays that contained the same values.
	@Override
	public boolean equals(Object y) {
		if (!(y instanceof Board))
			return false;

		Board other = (Board) y;
		return Arrays.deepEquals(tiles, other.tiles);
	}

	// Since we override equals(), we must also override hashCode(). When two
	// objects are
	// equal according to equals() they must return the same hashCode. More info:
	// -
	// http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java/27609#27609
	// - http://www.ibm.com/developerworks/library/j-jtp05273/
	@Override
	public int hashCode() {
		return Arrays.deepHashCode(tiles);
	}

	// return a Collection of all neighboring board positions
	public Collection<Board> neighbors() {
		Collection<Board> neighbors = new ArrayList<>();
		int[] emptyPosition = this.getPositionOfValue(0,this.getTiles());
		MoveDirection[] directions = new MoveDirection[] {MoveDirection.LEFT, MoveDirection.TOP, MoveDirection.RIGHT, MoveDirection.BOTTOM};
		for (MoveDirection direction : directions) {
			if(isValidMove(emptyPosition[0], emptyPosition[1], direction)) {
				neighbors.add(new Board(this.swapTiles(emptyPosition[0], emptyPosition[1], direction)));
			}
		}
		return neighbors;
	}

	// return a string representation of the board
	public String toString() {
		String result = "";
		for (int row = 0; row < this.getSize(); row++) {
			for (int column = 0; column < this.getSize(); column++) {
				int val = this.getValue(row, column);
				result +=  (val == 0 ? " " : val);
				result += ( (column == this.getSize()-1) ? "" : "\t" );
			}
			result+= "\n";
		}
		return result;
	}

	// is the initial board solvable? Note that the empty tile must
	// first be moved to its correct position.
	public boolean isSolvable() {
		int[][] emptyTileEnd = this.getEmptyTileAtEndBoard();
		int[] lookupHelper = this.lookupArray(emptyTileEnd);
		double totalNumerator = 1;
		double totalDenominator = 1;
		for (int j = 1; j < (this.getSize()*this.getSize()); j++) {
			for (int i = 1; i < j; i++) {
				totalNumerator   *= (lookupHelper[j] -lookupHelper[i]);
				totalDenominator *= (j-i);
			}
		}
		return (totalNumerator/totalDenominator)>=0;
	}

	public int[][] getTiles() {
		return tiles;
	}

	private void setTiles(int[][] tiles) {
		if (tiles == null || !this.isSquare(tiles)) {
			throw new IllegalArgumentException("Cannot set a tiles equals to null or when it is not a square");
		}
		this.tiles = tiles;
	}

	private int[][] getDeepCopy(int[][] tiles) {
		if (tiles == null) {
			return null;
		}
		int[][] copy = new int[tiles.length][];
		for (int i = 0; i < tiles.length; i++) {
			copy[i] = Arrays.copyOf(tiles[i], tiles[i].length);
		}
		return copy;
	}

	private int[] getPositionOfValue(int value, int[][] tiles) {
		int size = tiles.length;
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				if(this.getValue(row, column) == value) {
					return new int[] {row,column};	
				}
			}
		}
		return null;
	}
	
	private int[] lookupArray(int[][] tiles) {
		int[] lookup = new int[tiles.length*tiles.length];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				lookup[tiles[i][j]] = (i*lookup.length) + j + 1;
			}
		}
		System.out.println(Arrays.toString(lookup));
		return lookup;
	}
	
	private int[] getXYPosition(int value) {
		int row = (int) Math.ceil((double) value/ (double) this.getSize()) -1;
		int column = (int) (value -1) % this.getSize();
		return new int[] {row,column };
	}
	
	
	private boolean isValidMove(int row, int column, MoveDirection direction) {
		boolean result = false;
		switch (direction) {
		case LEFT:
			result = column > 0;
			break;
		case RIGHT:
			result = column < (this.getSize() -1);
			break;
		case TOP:
			result = row > 0;
			break;
		case BOTTOM:
			result = row < (this.getSize() -1);
			break;
		default:
			throw new IllegalArgumentException("Cannot make the move on the board");
		}
		return result;
	}
	
	private int[][] swapTiles(int row, int column, MoveDirection direction){
		return this.swapTiles(row, column, direction, this.getTiles());
	}
	
	private int[][] swapTiles(int row, int column, MoveDirection direction, int[][] tiles){
		switch (direction) {
		case LEFT:
			return this.swap(row, column, row, column-1,tiles);
		case RIGHT:
			return this.swap(row, column, row, column+1,tiles);
		case TOP:
			return this.swap(row, column, row-1, column,tiles);
		case BOTTOM:
			return this.swap(row, column, row+1, column,tiles);
		default:
			throw new IllegalArgumentException("Cannot make the move on the board");
		}
	}
		
	private int[][] swap(int frow, int fcol, int srow, int scol, int[][] tiles){
		int[][] swapedTitles = getDeepCopy(tiles);
		swapedTitles[frow][fcol] = tiles[srow][scol];
		swapedTitles[srow][scol] = tiles[frow][fcol];
		return swapedTitles;
	}
	
	public int[][] getEmptyTileAtEndBoard(){
		int[] pos = this.getPositionOfValue(0,this.getTiles());
		int rowMoves = this.getSize() - pos[0] -1;
		int colMoves = this.getSize() - pos[1] -1;
		int[][] currentTiles = this.getTiles();
		for (int i = 0; i < colMoves; i++) {
			currentTiles = this.swapTiles(pos[0], pos[1]+i, MoveDirection.RIGHT, currentTiles);
		}
		for (int i = 0; i < rowMoves; i++) {
			currentTiles = this.swapTiles(pos[0]+i, pos[1]+colMoves, MoveDirection.BOTTOM, currentTiles);
		}
		return currentTiles;
	}
	
	private int getValue(int row, int column) {
		return this.getTiles()[row][column];
	}

	private int getSize() {
		return this.getTiles().length;
	}
	
	private boolean isSquare(int[][] tiles) {
		if (tiles == null) {
			throw new IllegalArgumentException("Tiles cannot be null");
		}
		int length = tiles.length;
		for (int i = 0; length < i; i++) {
			if (tiles[i].length != length) {
				return false;
			}
		}
		return true;
	}

}
