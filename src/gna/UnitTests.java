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
										{13,15,14,12}};
  private final Board distanceBoard1 = new Board(distanceTiles1),
		              distanceBoard2 = new Board(distanceTiles2);
  
  /*@Test
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
  }*/
  
  @Test
  public void testManhattenDistanceWith4x4Example() {
	  System.out.println("yes");
		System.out.println(distanceBoard2.toString());
  }
  
}
