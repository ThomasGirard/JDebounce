package ch.arrg.jdebounce.test;

import org.junit.Test;

import ch.arrg.jdebounce.DebounceExecutor;
import ch.arrg.jdebounce.test.helpers.DebounceTest;
import ch.arrg.jdebounce.test.helpers.DebounceableImpl;
import ch.arrg.jdebounce.test.helpers.DebounceableInterface;
import ch.arrg.jdebounce.test.helpers.Scenario;

/**
 * This test shows how to debounce a method programmatically, without changing
 * the class to be debounced.
 */
public class ManualProxyTest {

	@Test
	public void testManual() throws InterruptedException {

		// Original instance
		DebounceableImpl test = new DebounceableImpl();

		// Debounced instance
		DebounceableInterface proxy = proxify(test);

		// Test the debounced method
		new DebounceTest().testDebouncing(proxy);
	}

	/** The method to debounce is DebounceableImpl.debouncedMethod. */
	private DebounceableInterface proxify(final DebounceableImpl test) {

		/** Return a new anonymous subclass with the debounced method. */
		return new DebounceableImpl() {
			DebounceExecutor exec = new DebounceExecutor();

			public void debouncedMethod(final int callID, final DebounceTest callback) {
				exec.debounce(Scenario.DEBOUNCE_DELAY_MS, new Runnable() {
					public void run() {
						// Call the original
						test.debouncedMethod(callID, callback);
					}
				});
			}
		};
	}
}
