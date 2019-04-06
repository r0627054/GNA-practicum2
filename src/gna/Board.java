package gna;

import java.util.Collection;
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
		throw new RuntimeException("not implemented"); // TODO
	}

	// return sum of Manhattan distances between blocks and goal
	public int manhattan() {
		throw new RuntimeException("not implemented"); // TODO
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
		throw new RuntimeException("not implemented"); // TODO
	}

	// return a string representation of the board
	public String toString() {
		return "<empty>"; // TODO
	}

	// is the initial board solvable? Note that the empty tile must
	// first be moved to its correct position.
	public boolean isSolvable() {
		
		throw new RuntimeException("not implemented"); // TODO
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

	private int getPositionOfValue(int value) {
		int size = this.getTiles().length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(this.getValue(i, j) == value) {
					return (size * i) + j + 1;
				}
			}
		}
		return -1;
	}
	
	private int[][] getEmptyTitleAtEndBoard(){
		return null;
	}
	
	private int getValue(int x, int y) {
		return this.getTiles()[x][y];
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
