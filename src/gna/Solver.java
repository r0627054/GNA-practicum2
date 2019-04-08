package gna;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import libpract.PriorityFunc;

public class Solver
{
	
	private BoardState boardStateSolution;
	
	/**
	 * Finds a solution to the initial board.
	 *
	 * @param priority is either PriorityFunc.HAMMING or PriorityFunc.MANHATTAN
	 */
	public Solver(Board initial, PriorityFunc priority)
	{
		if(initial== null || !initial.isSolvable()) {
			throw new IllegalArgumentException("Cannot solve a board which is null or unsolvable.");
		}
		// Use the given priority function (either PriorityFunc.HAMMING
		// or PriorityFunc.MANHATTAN) to solve the puzzle.
		if (priority == PriorityFunc.HAMMING) {
			this.solve(initial, new HammingComparator());
		} else if (priority == PriorityFunc.MANHATTAN) {
			this.solve(initial, new ManhattenComparator());
		} else {
			throw new IllegalArgumentException("Priority function not supported");
		}
	}
	
	
	private void solve(Board initial, Comparator<BoardState> comparator) {
		PriorityQueue<BoardState> prioQueue = new PriorityQueue<>(comparator);
		prioQueue.add(new BoardState(initial, null, 0));
		BoardState bestState = null;
		while(true) {
			 bestState = prioQueue.poll();
			 if(bestState.isCurrentSolved()) {
				 System.out.println("break");
				 break;
			 }
			 for (Board board : bestState.getCurrentNeighbors()) {
				if(bestState.getPreviousState() == null || !bestState.getPreviousState().getCurrentBoard().equals(board)) {
					prioQueue.add(new BoardState(board, bestState, bestState.getNbrMoves()+1));
				}
			}
		}
		this.setBoardStateSolution(bestState);
	}
	
	public BoardState getBoardStateSolution() {
		return boardStateSolution;
	}

	private void setBoardStateSolution(BoardState boardStateSolution) {
		this.boardStateSolution = boardStateSolution;
	}

	/**
	 * Returns a List of board positions as the solution. It should contain the initial
	 * Board as well as the solution (if these are equal only one Board is returned).
	 */
	public List<Board> solution()
	{
		ArrayList<Board> solution = new ArrayList<>();
		BoardState state = this.getBoardStateSolution();
		while(state != null) {
			solution.add(0,state.getCurrentBoard());
			state = state.getPreviousState();
		}
		return solution;
	}
}


