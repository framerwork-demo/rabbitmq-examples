package com.introfocus.examples.rabbitmq.pubsub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This test starts an application context that creates a RabbitMQ subscriber.
 * 
 * This should not be run as part of an automated test suite -- hence the name
 * ends in "s".
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/appContext-pubsub-subscriber.xml" })
public class RabbitMqSubscriberTests {

	@Test
	public void testStartRabbitMqDummyHandler() throws InterruptedException {
		Thread.sleep(Integer.MAX_VALUE);
	}

}
