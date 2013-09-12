package ch.arrg.jdebounce.test.helpers;

/**
 * When using Spring-aop, it is mandatory that the debounced methods correspond
 * to an interface. Otherwise proxying won't work.
 */
public interface DebounceableInterface {
	public void debouncedMethod(int callID, DebounceTest callback);
}
