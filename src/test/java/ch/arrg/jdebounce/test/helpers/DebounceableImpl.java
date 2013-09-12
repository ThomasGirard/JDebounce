package ch.arrg.jdebounce.test.helpers;

import ch.arrg.jdebounce.aop.Debounce;

public class DebounceableImpl implements DebounceableInterface {

	/** Here's the debounced method. */
	@Debounce(delayMilliseconds = Scenario.DEBOUNCE_DELAY_MS)
	public void debouncedMethod(int callID, DebounceTest callback) {
		callback.callback(callID);
	}
}
