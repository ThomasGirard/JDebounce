package ch.arrg.jdebounce.test;

import org.junit.Test;

import ch.arrg.jdebounce.DebounceExecutor;
import ch.arrg.jdebounce.DebouncerStore;
import ch.arrg.jdebounce.test.helpers.DebounceTest;
import ch.arrg.jdebounce.test.helpers.DebounceableImpl;
import ch.arrg.jdebounce.test.helpers.DebounceableInterface;
import ch.arrg.jdebounce.test.helpers.Scenario;

/**
 * This test shows how to debounce a method programmatically by explicitely
 * modifying the class.
 */
public class ExplicitTest {

	@Test
	public void testManual() throws InterruptedException {

		// Debounced instance
		DebounceableInterface target = new ExplicitDebounceable();

		// Test the debounced method
		new DebounceTest().testDebouncing(target);
	}
}

class ExplicitDebounceable extends DebounceableImpl {
	DebouncerStore<String> store = new DebouncerStore<String>();

	public void debouncedMethod(final int callID, final DebounceTest callback) {
		DebounceExecutor exec = store.registerOrGetDebouncer("debouncedMethod");

		exec.debounce(Scenario.DEBOUNCE_DELAY_MS, new Runnable() {
			public void run() {
				ExplicitDebounceable.super.debouncedMethod(callID, callback);
			}
		});
	};
}
