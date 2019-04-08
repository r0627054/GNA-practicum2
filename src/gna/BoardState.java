package gna;

import java.util.Collection;

/**
 * A board state contains the previous BoardState (this can be null if there is no previous BoardState.
 *  It also contains the current board, and the number of moves needed to go from the initial board up to this board.
 * 
 * @author Dries Janse
 * @version 1.0
 */
public class BoardState {
	
	/**
	 * Variable storing the current board.
	 */
	private Board currentBoard;
	
	/**
	 * Variable storing the previous state.
	 */
	private BoardState previousState;
	
	/**
	 * Variable storing the number of moves to get to this state.
	 */
	private int nbrMoves;
	
	/**
	 * Initialises a board with all the given variables.
	 * @param currentBoard  The board of the current state.
	 * @param previousState The previous state of the board.
	 * @param nbrMoves      The number of moves needed to get to this state.
	 * @effect All the given variables are set.
	 *         |this.setCurrentBoard(currentBoard);
	 *         |this.setPreviousState(previousState);
	 *         |this.setNbrMoves(nbrMoves);
	 */
	public BoardState(Board currentBoard, BoardState previousState, int nbrMoves) {
		this.setCurrentBoard(currentBoard);
		this.setPreviousState(previousState);
		this.setNbrMoves(nbrMoves);
	}

	/**
	 * Returns the Manhattan distance of the current board.
	 * @return The Manhattan distance of the current board.
	 */
	public int getCurrentBoardManhattan() {
		return this.getCurrentBoard().manhattan();
	}
	
	/**
	 * Returns the Hamming distance of the current board.
	 * @return the Hamming distance of the current board.
	 */
	public int getCurrentBoardHamming() {
		return this.getCurrentBoard().hamming();
	}
	
	/**
	 * Returns a collection of neighbours of the currentBoard.
	 * @return a collection of neighbours of the currentBoard.
	 */
	public Collection<Board> getCurrentNeighbors(){
		return this.getCurrentBoard().neighbors();
	}
	
	/**
	 * Checks whether the current board is solved or not.
	 * @return True when the current board is solved; otherwise false.
	 */
	public boolean isCurrentSolved() {
		return this.getCurrentBoard().isSolved();
	}
	
	/**
	 * Returns the currentBoard variable.
	 * @return the currentBoard variable.
	 */
	public Board getCurrentBoard() {
		return currentBoard;
	}
	
	/**
	 * Sets the current Board variable.
	 * @param currentBoard The board to which current board will be set. 
	 * @effect The currentBoard variable equals the currentBoard parameter.
	 *         | new.getCurrentBoard() == currentBoard.
 	 */
	private void setCurrentBoard(Board currentBoard) {
		this.currentBoard = currentBoard;
	}
	
	/**
	 * Returns the previous state variable.
	 * @return The previous state variable.
	 */
	public BoardState getPreviousState() {
		return previousState;
	}
	
	/**
	 * Sets the previous state variable.
	 * @param previousState The previous state to which the previous state variable will be set.
	 * @effect The previousState variable equals the given parameter.
	 *         | new.getPreviousState() == previousState
	 */
	private void setPreviousState(BoardState previousState) {
		this.previousState = previousState;
	}

	/**
	 * Returns the number of moves variable.
	 * @return The number of moves variable.
	 */
	public int getNbrMoves() {
		return nbrMoves;
	}

	/**
	 * Sets the number of moves variable.
	 * @param nbrMoves The nbrMoves to which the nbrMoves variable will be set.
	 * @effect The nbrMoves variable equals the nbrMoves parameter
	 *         | new.getNbrMoves() == nbrMoves
	 */
	private void setNbrMoves(int nbrMoves) {
		this.nbrMoves = nbrMoves;
	}
	
}
