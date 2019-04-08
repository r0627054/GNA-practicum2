package gna;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The board represents the a matrix (2D-array) of tiles.
 *  The class contains multiple functions for changing the tiles or calculating data out of it.
 * 
 * @author Dries Janse
 * @version 1.0
 */
public class Board {

	/**
	 * Variable storing the 2D array containing the values.
	 */
	private int[][] tiles;

	/**
	 * Initialises a new board with the given tiles. The tiles stored are a deep copy of the given tiles.
	 * 
	 * @param tiles The tile of which a deep copy will be stored.
	 * @post A deep copy of the tiles is set to the tiles variable.
	 *       | this.setTiles(this.getDeepCopy(tiles))
	 */
	public Board(int[][] tiles) {
		this.setTiles(this.getDeepCopy(tiles));
	}

	/**
	 * Returns the Hamming distance of the board.
	 *  The Hamming distance equals the number of blocks out of place.
	 * @return The Hamming distance.
	 */
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

	/**
	 * Returns the sum of Manhattan distances between blocks and goal.
	 * @return The sum of the Manhattan distances.
	 */
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
	
	/**
	 * Calculates one Manhattan distance for a value.
	 *  The given row and column are the one of which the given value are positioned.
	 *  Both need to be subtracted from the actual position the value need to be (for the solution).
	 *  The sum of these differences is the result.
	 * @param row    The row position of the value.
	 * @param column The column position of the value.
	 * @param value  The value of which the distance needs to be calculated.
	 * @return The distance between the actual value position and the solution value position.
	 */
	private int getDistance(int row, int column, int value) {	
		int[] pos = this.getXYPosition(value);
		return Math.abs(row - pos[0]) + Math.abs(column - pos[1]);
	}

	// 
	
	/**
	 * Does this board equal y. Two boards are equal when they both were constructed
	 *  using tiles[][] arrays that contained the same values.
	 * @param y The board which needs to be compared with.
	 * @return True when the tiles[][] array contain the same values; otherwise false.
	 */
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
 
	/**
	 * Returns a Collection of all neighbouring board positions.
	 * @return a Collection of all neighbouring board positions.
	 */
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

	/**
	 * Returns a string representation of the board.
	 * @return a string representation of the board.
	 */
	@Override
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

	/**
	 * Checks whether the board is solvable.
	 *  The empty tile is moved to the correct position (bottom, right) position.
	 * @return True when the initial board is solvable; otherwise false.
	 */
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

	/**
	 * Returns the tiles variable.
	 * @return the tiles variable.
	 */
	public int[][] getTiles() {
		return tiles;
	}

	/**
	 * Sets the tites variable.
	 * @param tiles The titles which will be set for the tiles instance variable.
	 * @throws IllegalArgumentException When tiles equals null or when the tiles parameter does not represent a matrix.
	 *                                  | tiles == null || !this.isSquare(tiles)
	 * @post The tiles variables is set.
	 *       | new.getTiles() == tiles
	 */
	private void setTiles(int[][] tiles) {
		if (tiles == null || !this.isSquare(tiles)) {
			throw new IllegalArgumentException("Cannot set a tiles equals to null or when it is not a square");
		}
		this.tiles = tiles;
	}

	/**
	 * Returns a deep copy of the given tiles variable.
	 *   All the values need to be copied to another 2D array.
	 *   (This copy is returned)
	 * @param tiles The tiles 2D array of which a deep copy has to be taken.
	 * @return A deep copy of the given tiles parameter.
	 */
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

	/**
	 * Returns the position where the given value is located.
	 *  When the value cannot be found, null is returned.
	 * @param value The value of which the position needs to be returned.
	 * @param tiles The tiles in which the value needs to searched.
	 * @return The position if the tiles parameter contains the value; otherwise null.
	 *         The position first element is the row index, the second element is the column index.
	 * @throws IllegalArgumentException when the tiles variable equals null.
	 */
	private int[] getPositionOfValue(int value, int[][] tiles) {
		if(tiles == null) {
			throw new IllegalArgumentException("Tiles cannot be null");
		}
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
	
	/**
	 * Returns the lookupArray of the given tiles.
	 *  A lookupArray is an array which stores the position of the value.
	 *  The index is the value of a original tile, the element on that index is the position of the original tile.
	 * @param tiles The tiles 2D array of which a lookupArray needs to be created.
	 * @return The computed lookupArray.
	 */
	private int[] lookupArray(int[][] tiles) {
		int[] lookup = new int[tiles.length*tiles.length];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				lookup[tiles[i][j]] = (i*tiles.length) + j + 1;
			}
		}
		return lookup;
	}
	
	/**
	 * Returns the solution XY-position of the given value.
	 * @param value The value of which the solution position needs to be calculated.
	 * @return The solution position of the given value. It is an array of 2 integers.
	 *         The first integer is the row index, the second is the column index.
	 */
	private int[] getXYPosition(int value) {
		int row = (int) Math.ceil((double) value/ (double) this.getSize()) -1;
		int column = (int) (value -1) % this.getSize();
		return new int[] {row,column };
	}
	
	/**
	 * Checks whether a move is valid.
	 *  A move is valid when it does not go out of the matrix borders.
	 * @param row       The starting row index.
	 * @param column    The starting column index.
	 * @param direction The direction of the move.
	 * @return True when the move on the current position can be made (does not go out of the matrix borders); otherwise false.
	 */
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
	
	/**
	 * Swaps the title on the given position with the tile of the given move direction.
	 *  By default the tile swap is done on a deep copy of the titles of the board.
	 * @param row       The first tile row index.
	 * @param column    The first tile column index.
	 * @param direction The direction with which tile needs to be swapped.
	 * @return The tiles (2D array) with the performed swap.
	 */
	private int[][] swapTiles(int row, int column, MoveDirection direction){
		return this.swapTiles(row, column, direction, this.getTiles());
	}
	
	/**
	 * Swaps the title on the given position with the tile of the given move direction.
	 *  The swap is performed on the given 2D tiles array.
	 * @param row       The first tile row index.
	 * @param column    The first tile column index.
	 * @param direction The direction with which tile needs to be swapped.
	 * @param tiles     The tiles (of which first a deep copy will be taken) of which the swap will be done.
	 * @return Swaps the tile with the tile in the given direction.
	 */
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
	
	/**
	 * Creates a deep copy of the tiles, with the 2 element swapped on the corresponding indexes.
	 * @param frow  The row index of the first element which will be swapped.
	 * @param fcol  The column index of the first element which will be swapped.
	 * @param srow  The row index of the second element which will be swapped.
	 * @param scol  The column index of the second element which will be swapped.
	 * @param tiles The tiles (of which first a deep copy will be taken) of which the swap will be done.
	 * @return The 2D tiles array after the swap.
	 */
	private int[][] swap(int frow, int fcol, int srow, int scol, int[][] tiles){
		int[][] swapedTitles = getDeepCopy(tiles);
		swapedTitles[frow][fcol] = tiles[srow][scol];
		swapedTitles[srow][scol] = tiles[frow][fcol];
		return swapedTitles;
	}
	
	/**
	 * Moves the zero value with valid moves to the end position (bottom right position).
	 * @return The tiles 2D array where the zero is in its end position.
	 */
	public int[][] getEmptyTileAtEndBoard(){
		int[] pos = this.getPositionOfValue(0,this.getTiles());
		int rowMoves = this.getSize() - pos[0] -1;
		int colMoves = this.getSize() - pos[1] -1;
		int[][] currentTiles = this.getDeepCopy(this.getTiles());
		for (int i = 0; i < colMoves; i++) {
			currentTiles = this.swapTiles(pos[0], pos[1]+i, MoveDirection.RIGHT, currentTiles);
		}
		for (int i = 0; i < rowMoves; i++) {
			currentTiles = this.swapTiles(pos[0]+i, pos[1]+colMoves, MoveDirection.BOTTOM, currentTiles);
		}
		return currentTiles;
	}
	
	/**
	 * Returns the value on the given row index and column index.
	 * @param row    The row index of the requested value.
	 * @param column The column index of the requested value.
	 * @return The value on the given value.
	 *         | this.getTiles()[row][column]
	 */
	private int getValue(int row, int column) {
		return this.getTiles()[row][column];
	}

	/**
	 * Returns the size of the tiles.
	 * @return the size of the tiles.
	 *         | this.getTiles().length
	 */
	private int getSize() {
		return this.getTiles().length;
	}
	
	/**
	 * Checks whether the given tiles matrix (2D-array) is square.
	 * @param tiles The tiles matrix which will be check.
	 * @return True when the matrix is a square; otherwise false.
	 */
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
	
	/**
	 * Returns whether the Board is in its solved state.
	 *  True when all the values are in there end position.
	 * @return True when all the values are in there end position; otherwise false.
	 */
	public boolean isSolved() {
		int current = 1;
		for (int row = 0; row < this.getSize(); row++) {
			for (int col = 0; col < this.getSize(); col++) {
				if(this.getValue(row, col) != current && current != (this.getSize()*this.getSize()) ) {
					return false;
				}
				current++;
			}
		}
		return true;
	}

}
