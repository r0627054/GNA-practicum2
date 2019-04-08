package gna;

import java.util.Comparator;

public class HammingComparator implements Comparator<BoardState> {

	@Override
	public int compare(BoardState state1, BoardState state2) {
		return (state1.getCurrentBoardHamming() + state1.getNbrMoves()) - (state2.getCurrentBoardHamming() + state2.getNbrMoves());
	}

}
