package gna;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import libpract.PriorityFunc;

public class Solver
{
	/**
	 * Variable storing the solution state of the board.
	 *  (By looking at the previous state, the previous boards can be calculated.
	 */
	private BoardState boardStateSolution;
	
	/**
	 * Finds a solution to the initial board.
	 * @param priority is either PriorityFunc.HAMMING or PriorityFunc.MANHATTAN
	 * @throws IllegalArgumentException when the function cannot be solved or when the priorityFunction does not equal HAMMING OR MANHATTAN.
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
			this.solve(initial, new ManhattanComparator());
		} else {
			throw new IllegalArgumentException("Priority function not supported");
		}
	}
	
	/**
	 * Solves the actual board by making use of the comparator.
	 * @param initial    The initial board.
	 * @param comparator The comparator used in the priority queue.
	 * @effect The solution is computed and the final solution state is stored in the boardStateSolution.
	 */
	private void solve(Board initial, Comparator<BoardState> comparator) {
		PriorityQueue<BoardState> prioQueue = new PriorityQueue<>(comparator);
		prioQueue.add(new BoardState(initial, null, 0));
		BoardState bestState = null;
		while(true) {
			 bestState = prioQueue.poll();
			 if(bestState.isCurrentSolved()) {
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
	
	/**
	 * Returns the board state solution variable.
	 * @return the board state solution variable.
	 */
	public BoardState getBoardStateSolution() {
		return boardStateSolution;
	}

	/**
	 * Sets the board state solution variable.
	 * @param boardStateSolution The BoardState to which the board state solution variable will be set.
	 * @post The boardStateSolution equals the parameter.
	 *       | new.getBoardStateSolution() == boardStateSolution
	 */
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
	
	public int getMinimalNumberOfMoves() {
		return this.getBoardStateSolution().getNbrMoves();
	}
}


