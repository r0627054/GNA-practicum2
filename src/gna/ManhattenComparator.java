package gna;

import java.util.Comparator;

public class ManhattenComparator implements Comparator<BoardState> {

	@Override
	public int compare(BoardState state1, BoardState state2) {
		return (state1.getCurrentBoardManhattan() + state1.getNbrMoves()) - (state2.getCurrentBoardManhattan() + state2.getNbrMoves());
	}

}
