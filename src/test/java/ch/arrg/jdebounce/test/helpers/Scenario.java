package ch.arrg.jdebounce.test.helpers;

import java.util.HashSet;
import java.util.Set;

/** Static members defining a test scenario. */
public class Scenario {
	/** How often the debounced method is supposed to be called. */
	public static final long DEBOUNCE_DELAY_MS = 100;

	/** A method call will be made at these given delays. */
	public static final int[] delays = new int[] { 0, 200, 0, 10, 20, 30, 120, 120, 30, 30, 80, 120 };

	/**
	 * Returns the indexes of the calls that are supposed to actually be
	 * executed.
	 */
	public static Set<Integer> expectedIndexes() {
		HashSet<Integer> expected = new HashSet<Integer>();

		for (int i = 1; i < delays.length; i++) {
			if (delays[i] >= DEBOUNCE_DELAY_MS) {
				expected.add(i - 1);
			}
		}

		// Last call always goes through.
		expected.add(delays.length - 1);

		return expected;
	}

	/**
	 * @param received
	 *            a set of integers that are indexes corresponding to received
	 *            calls.
	 * @return true iff the received calls are the same as were expected for the
	 *         scenario.
	 */
	public static boolean checkReceived(Set<Integer> received) {
		return expectedIndexes().equals(received);
	}
}
