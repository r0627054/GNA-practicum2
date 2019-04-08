package gna;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The boardReader will help by reading board our of textfiles and,
 *  and returning it in board objects.
 * @author Dries Janse
 * @version 1.0
 */
public class BoardReader {

	/**
	 * Reads a file containing a puzzle an converts it to a Board.
	 * @param puzzlePath  The path of the file containing the puzzle.
	 * @return The board generated out of the puzzle file.
	 */
	@SuppressWarnings("resource")
	public static Board getBoardWithData(String puzzlePath) {
		Scanner s;
		try {
			s = new Scanner(new File(puzzlePath));
			int size = s.nextInt();
			int[][] tiles = new int[size][size];
			for (int i = 0; i < size; i++)
				for (int j = 0; j < size; j++)
					tiles[i][j] = s.nextInt();
			
			return new Board(tiles);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
