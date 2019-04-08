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
  public void insolvableBoard() {
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
  
  
}
