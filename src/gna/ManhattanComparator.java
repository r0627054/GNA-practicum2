package gna;

import java.util.Comparator;

/**
 * Comparator used for comparing BoardStates.
 *  The comparator makes use of the Manhattan priority function principle.
 * @author Dries Janse
 * @version 1.0
 */
public class ManhattanComparator implements Comparator<BoardState> {

	/**
	 * Compare 2 BoardStates using the Manhattan priority function of the current board with the current boards number of moves.
	 * {@inheritDoc}
	 */
	@Override
	public int compare(BoardState state1, BoardState state2) {
		return (state1.getCurrentBoardManhattan() + state1.getNbrMoves()) - (state2.getCurrentBoardManhattan() + state2.getNbrMoves());
	}

}
