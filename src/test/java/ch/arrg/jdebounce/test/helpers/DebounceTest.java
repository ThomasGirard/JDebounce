package ch.arrg.jdebounce.test.helpers;

import java.util.Set;

import org.junit.Assert;

public class DebounceTest {

	private Set<Integer> expected;

	/**
	 * Executes the {@link Scenario} on the given debounced instance.
	 * 
	 * @param target
	 *            a debounce-enabled instance
	 */
	public void testDebouncing(DebounceableInterface target) throws InterruptedException {
		expected = Scenario.expectedIndexes();

		// Iterate over all delays in the scenario, calling the method each time.
		int[] delays = Scenario.delays;
		for (int i = 0; i < delays.length; i++) {
			int delay = delays[i];

			Thread.sleep(delay);

			target.debouncedMethod(i, this);
		}

		// Wait for a while otherwise we may not receive the last call. 
		Thread.sleep(Scenario.DEBOUNCE_DELAY_MS * 2);

		if (!expected.isEmpty()) {
			Assert.fail(expected.size() + " calls should have gone through but did not.");
		}
	}

	/**
	 * The method calls back when executed, we collect the calls that get
	 * through.
	 */
	void callback(int callID) {
		if (!expected.contains(callID)) {
			Assert.fail("Call " + callID + " should not have gone through.");
		} else {
			expected.remove(callID);
		}
	}
}
