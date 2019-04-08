package gna;

/**
 * The stopWatch class as is from the course book.
 *
 */
public class Stopwatch {

	/**
	 * Variable storing the current time in milliseconds at creation of the object.
	 */
	private final long start;
	
	/**
	 * Initialise a stopWatch with the current time.
	 */
	public Stopwatch() {
		start = System.currentTimeMillis();
	}
	
	/**
	 * Returns the elapsedTime in milliseconds.
	 */
	public long elapsedTime() {
		long now = System.currentTimeMillis();
		//return (now-start)/1000.0;
		return now - start;
	}
	
}
