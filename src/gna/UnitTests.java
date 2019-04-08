package gna;

import java.util.Collection;
import java.util.List;
import libpract.PriorityFunc;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * A number of JUnit tests for Solver.
 *
 * Feel free to modify these to automatically test puzzles or other functionality
 */
public class UnitTests {
  
  private final int distanceTiles1[][]= {{8,1,3},
		  								{4,0,2},
		  								{7,6,5}},
                    distanceTiles2[][]= {{3,1,6,4},
										{5,0,9,7},
										{10,2,11,8},
										{13,15,14,12}},
                   unsolvableTiles[][]= {{1,2,3},
										{4,6,5},
										{7,8,0}},
                   solved3x3[][]      = {{1,2,3},
									     {4,5,6},
									     {7,8,0}};
                   
  	
  private final Board distanceBoard1 = new Board(distanceTiles1),
		              distanceBoard2 = new Board(distanceTiles2),
		              unSolvableBoard= new Board(unsolvableTiles),
		              solved3x3Board = new Board(solved3x3);
  private final String puzzel04 = "boards/puzzle04.txt",
		               puzzel20 = "boards/puzzle20.txt",
                       puzzel22 = "boards/puzzle22.txt",
                       puzzel24 = "boards/puzzle24.txt",
                       puzzel26 = "boards/puzzle26.txt",
                       unsolvable= "boards/puzzle3x3-impossible.txt";
 
  @Test (expected = IllegalArgumentException.class)
  public void solveAnImpossiblePuzzelWillThrowException() {
	  new Solver(BoardReader.getBoardWithData(unsolvable), PriorityFunc.MANHATTAN);
  }
  
  @Test
  public void testHammingDistanceWithAssignmentExample() {
		assertEquals(5, distanceBoard1.hamming());
  }
  
  @Test
  public void testHammingDistanceWith4x4Example() {
		assertEquals(11, distanceBoard2.hamming());
  }
  
  @Test
  public void testManhattenDistanceWithAssignmentExample() {
		assertEquals(10, distanceBoard1.manhattan());
  }

  @Test
  public void testManhattenDistanceWith4x4Example() {
		assertEquals(16, distanceBoard2.manhattan());
  }
  
  @Test
  public void testSolvableBoard() {
	 assertTrue(distanceBoard2.isSolvable());
  }
  
  @Test
  public void unsolvableBoard() {
	  assertFalse(unSolvableBoard.isSolvable());
  }
  
  @Test
  public void isSolvedWithSolvedExampleTest() {
	  assertTrue(solved3x3Board.isSolved());
  }
  
  @Test
  public void isSolvedWithUnSolvedExampleTest() {
	  assertFalse(distanceBoard1.isSolved());
  }
  
  @Test
  public void puzzel4HamminghasCorrectMinimalMoves() {
	  Board b = BoardReader.getBoardWithData(puzzel04);
	  assertEquals(4, new Solver(b, PriorityFunc.HAMMING).getMinimalNumberOfMoves());
  }
  
  @Test
  public void puzzel20HamminghasCorrectMinimalMoves() {
	  Board b = BoardReader.getBoardWithData(puzzel20);
	  assertEquals(14, new Solver(b, PriorityFunc.HAMMING).getMinimalNumberOfMoves());
  } 
  
  @Test
  public void puzzel22HamminghasCorrectMinimalMoves() {
	  Board b = BoardReader.getBoardWithData(puzzel22);
	  assertEquals(12, new Solver(b, PriorityFunc.HAMMING).getMinimalNumberOfMoves());
  }
  
  @Test
  public void puzzel24HamminghasCorrectMinimalMoves() {
	  Board b = BoardReader.getBoardWithData(puzzel24);
	  assertEquals(3, new Solver(b, PriorityFunc.HAMMING).getMinimalNumberOfMoves());
  }
  
  @Test
  public void puzzel26HamminghasCorrectMinimalMoves() {
	  Board b = BoardReader.getBoardWithData(puzzel26);
	  assertEquals(2, new Solver(b, PriorityFunc.HAMMING).getMinimalNumberOfMoves());
  }
  
  @Test
  public void puzzel4IsSolvedCorrectlyWithHamming() {
	  Board b = BoardReader.getBoardWithData(puzzel04);
	  Solver s = new Solver(b, PriorityFunc.HAMMING);
	  assertTrue(s.getBoardStateSolution().getCurrentBoard().isSolved());
  }
  
  @Test
  public void puzzel20IsSolvedCorrectlyWithHamming() {
	  Board b = BoardReader.getBoardWithData(puzzel20);
	  Solver s = new Solver(b, PriorityFunc.HAMMING);
	  assertTrue(s.getBoardStateSolution().getCurrentBoard().isSolved());
  }

  @Test
  public void puzzel22IsSolvedCorrectlyWithHamming() {
	  Board b = BoardReader.getBoardWithData(puzzel22);
	  Solver s = new Solver(b, PriorityFunc.HAMMING);
	  assertTrue(s.getBoardStateSolution().getCurrentBoard().isSolved());
  }
  
  @Test
  public void puzzel24IsSolvedCorrectlyWithHamming() {
	  Board b = BoardReader.getBoardWithData(puzzel24);
	  Solver s = new Solver(b, PriorityFunc.HAMMING);
	  assertTrue(s.getBoardStateSolution().getCurrentBoard().isSolved());
  }
  
  @Test
  public void puzzel26IsSolvedCorrectlyWithHamming() {
	  Board b = BoardReader.getBoardWithData(puzzel26);
	  Solver s = new Solver(b, PriorityFunc.HAMMING);
	  assertTrue(s.getBoardStateSolution().getCurrentBoard().isSolved());
  }
    
  @Test
  public void puzzel4ManhattanghasCorrectMinimalMoves() {
	  Board b = BoardReader.getBoardWithData(puzzel04);
	  assertEquals(4, new Solver(b, PriorityFunc.MANHATTAN).getMinimalNumberOfMoves());
  }
  
  @Test
  public void puzzel20ManhattanhasCorrectMinimalMoves() {
	  Board b = BoardReader.getBoardWithData(puzzel20);
	  assertEquals(14, new Solver(b, PriorityFunc.MANHATTAN).getMinimalNumberOfMoves());
  } 
  
  @Test
  public void puzzel22ManhattanhasCorrectMinimalMoves() {
	  Board b = BoardReader.getBoardWithData(puzzel22);
	  assertEquals(12, new Solver(b, PriorityFunc.MANHATTAN).getMinimalNumberOfMoves());
  }
  
  @Test
  public void puzzel24ManhattanhasCorrectMinimalMoves() {
	  Board b = BoardReader.getBoardWithData(puzzel24);
	  assertEquals(3, new Solver(b, PriorityFunc.MANHATTAN).getMinimalNumberOfMoves());
  }
  
  @Test
  public void puzzel26ManhattanhasCorrectMinimalMoves() {
	  Board b = BoardReader.getBoardWithData(puzzel26);
	  assertEquals(2, new Solver(b, PriorityFunc.MANHATTAN).getMinimalNumberOfMoves());
  }
  
  @Test
  public void puzzel4IsSolvedCorrectlyWithManhattan() {
	  Board b = BoardReader.getBoardWithData(puzzel04);
	  Solver s = new Solver(b, PriorityFunc.MANHATTAN);
	  assertTrue(s.getBoardStateSolution().getCurrentBoard().isSolved());
  }
  
  @Test
  public void puzzel20IsSolvedCorrectlyWithManhattan() {
	  Board b = BoardReader.getBoardWithData(puzzel20);
	  Solver s = new Solver(b, PriorityFunc.MANHATTAN);
	  assertTrue(s.getBoardStateSolution().getCurrentBoard().isSolved());
  }

  @Test
  public void puzzel22IsSolvedCorrectlyWithManhattan() {
	  Board b = BoardReader.getBoardWithData(puzzel22);
	  Solver s = new Solver(b, PriorityFunc.MANHATTAN);
	  assertTrue(s.getBoardStateSolution().getCurrentBoard().isSolved());
  }
  
  @Test
  public void puzzel24IsSolvedCorrectlyWithManhattan() {
	  Board b = BoardReader.getBoardWithData(puzzel24);
	  Solver s = new Solver(b, PriorityFunc.MANHATTAN);
	  assertTrue(s.getBoardStateSolution().getCurrentBoard().isSolved());
  }
  
  @Test
  public void puzzel26IsSolvedCorrectlyWithManhattan() {
	  Board b = BoardReader.getBoardWithData(puzzel26);
	  Solver s = new Solver(b, PriorityFunc.MANHATTAN);
	  assertTrue(s.getBoardStateSolution().getCurrentBoard().isSolved());
  }
  
}
