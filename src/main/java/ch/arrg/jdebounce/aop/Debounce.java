package ch.arrg.jdebounce.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used on a method to enable debouncing calls.
 * 
 * Debouncing is a technique that cancels invocations of a method when they're
 * too close in time. The idea is that a delay D is defined such that:
 * <ul>
 * <li>Each method call M is delayed by D.</li>
 * <li>When a new method call M' occurs, if a previous call M was not yet
 * executed, it is canceled. And M' is put to waiting instead.</li>
 * <li>When no new method call occurs during the delay D, then the currently
 * waiting call is effectively executed.</li>
 * </ul>
 * 
 * Consequence of the above, debounced methods are asynchronous. Since in
 * general a method annotated with {@link Debounce} may be synchronous, there's
 * no way in Java to ensure the method's contract is respected.
 * 
 * Debounced methods will always return null (or void for void methods).
 * Debouncing is best used with methods that are already asynchronous.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Debounce {
	long delayMilliseconds() default 0;
}
