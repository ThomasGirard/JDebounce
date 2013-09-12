package ch.arrg.jdebounce.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import ch.arrg.jdebounce.DebounceExecutor;
import ch.arrg.jdebounce.DebouncerStore;

public class DebounceMethodInterceptor implements MethodInterceptor {
	private static DebouncerStore<Method> store = new DebouncerStore<Method>();

	public DebounceMethodInterceptor() {

	}

	public Object invoke(final MethodInvocation invocation) throws Throwable {
		/*
		 * TODO Annotation could have a discrimination policy for grouping
		 * invocations. For example if you debounce public void updateTab(int
		 * tabIndex); The policy would separately debounce calls for each index.
		 */

		Method method = invocation.getMethod();
		Debounce annotation = resolveAnnotation(method);
		long delay = annotation.delayMilliseconds();

		DebounceExecutor debouncer = store.registerOrGetDebouncer(method);

		debounceCall(invocation, delay, debouncer);

		return null;
	}

	private void debounceCall(final MethodInvocation invocation, long delay, DebounceExecutor debouncer) {
		debouncer.debounce(delay, new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					Object result = invocation.proceed();
					// TODO Annotation could provide a callback for result
				} catch (Throwable e) {
					// TODO Annotation could provide a callback for exceptions
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Retrieve the @{@link Debounce} annotation for this method.
	 * 
	 * @param invocation
	 *            the method invocation
	 * @return the annotation
	 */
	private static Debounce resolveAnnotation(Method method) {
		Debounce annotation = method.getAnnotation(Debounce.class);
		return annotation;
	}

}
