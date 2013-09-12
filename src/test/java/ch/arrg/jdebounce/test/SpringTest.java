package ch.arrg.jdebounce.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.arrg.jdebounce.test.helpers.DebounceTest;
import ch.arrg.jdebounce.test.helpers.DebounceableInterface;

/** Tests debouncing with Spring-AOP. */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:jdebounce-context-test.xml" })
public class SpringTest {

	/**
	 * The target must be managed in the spring context (so that it is
	 * proxified) and must have an interface declaration (proxiying doesn't work
	 * on concrete classes or something ?).
	 */
	@Autowired
	DebounceableInterface target;

	@Test
	public void testDebounceSpring() throws InterruptedException {
		new DebounceTest().testDebouncing(target);
	}
}
