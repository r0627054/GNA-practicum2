package gna;

import libpract.StdIn;

import java.io.File;
import java.io.FileOutputStream;

import libpract.PriorityFunc;

class Main
{
	public static void main( String[] args )
	{
		
		/*PriorityFunc[] prio = {PriorityFunc.HAMMING};
		String[] paths = {"boards/puzzle04.txt","boards/puzzle20.txt","boards/puzzle22.txt","boards/puzzle24.txt","boards/puzzle26.txt","boards/puzzle28.txt",
				"boards/puzzle30.txt","boards/puzzle32.txt","boards/puzzle34.txt","boards/puzzle36.txt","boards/puzzle38.txt","boards/puzzle40.txt","boards/puzzle42.txt" };
		
		
		String[] paths = {"boards/puzzle28.txt",
				"boards/puzzle30.txt" };
		
		
		generateDataCSV(paths, prio);
		*/
		
		
		int N = StdIn.readInt();
		int[][] tiles = new int[N][N];
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				tiles[i][j] = StdIn.readInt();
		
		Board initial = new Board(tiles);
		if (!initial.isSolvable())
		{
			System.out.println("No solution possible");
		}
		else
		{
			Solver solver = new Solver(initial, PriorityFunc.HAMMING);
	
			for (Board board : solver.solution())
				System.out.println(board);

			System.out.println("Minimum number of moves = " + Integer.toString(solver.solution().size() - 1));
		}
	}
	
	
	/**
	 * Generates a CSV file with containing all the information of the puzzle efficiency and the used priorityFunction.
	 * @param filePaths     The list of path to the puzzle files.
	 * @param priorityFuncs The priority functions used.
	 * @post A CSV file is generated with the given information.
	 */
	private static void generateDataCSV(String[] filePaths, PriorityFunc[] priorityFuncs) {
		String filename=System.getProperty("user.dir") + File.separator + "dataFile.csv";
		try (FileOutputStream fos = new FileOutputStream(filename)) {
			for(PriorityFunc prio: priorityFuncs) {
				String priorityFuncLine = prio.name() + "\n";
				priorityFuncLine += "file,time,minimalmoves"+ "\n";
				fos.write(priorityFuncLine.getBytes());
				for (String p: filePaths) {
					Board b = BoardReader.getBoardWithData(p);
					Stopwatch stopwatch = new Stopwatch();
					Solver s = new Solver(b, prio);
					long time = stopwatch.elapsedTime();
					String dataLine = p +"," +time + "," + s.getMinimalNumberOfMoves() +  "\n";
					fos.write(dataLine.getBytes());
				}
			}
		}catch (Exception e) {
			throw new IllegalArgumentException("Couldn't save the content");
		}
	}
	
	
}


