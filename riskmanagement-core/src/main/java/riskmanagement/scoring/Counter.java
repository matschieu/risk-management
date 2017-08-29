package riskmanagement.scoring;

/**
 *
 * A simple counter.
 *
 * @author Matschieu
 *
 */
public class Counter {

	private int value = 0;

	/**
	 * Increment the counter (+1)
	 */
	public void increment() {
		value++;
	}

	/**
	 *
	 * @return int: the current value of the counter
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Reset the counter to 0
	 */
	public void reset() {
		value = 0;
	}

}
