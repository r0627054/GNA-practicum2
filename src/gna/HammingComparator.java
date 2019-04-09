package gna;

import java.util.Comparator;

/**
 * Comparator used for comparing BoardStates.
 *  The comparator makes use of the Hamming priority function principle.
 * @author Dries Janse
 * @version 1.0
 */
public class HammingComparator implements Comparator<BoardState> {

	/**
	 * Compare 2 BoardStates using the Hamming priority function of the current boards with the current boards number of moves.
	 * {@inheritDoc}
	 */
	@Override
	public int compare(BoardState state1, BoardState state2) {
		return (state1.getCurrentBoardHamming() + state1.getNbrMoves()) - (state2.getCurrentBoardHamming() + state2.getNbrMoves());
	}

}
