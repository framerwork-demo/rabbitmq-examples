package com.introfocus.examples.rabbitmq.rpc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The purpose of this test is simply to test RPC request/response correlation
 * and handling with Rabbit MQ.
 * 
 * You should run this test first. This starts the Rabbit MQ queue listeners.
 * Then run {@link RabbitMqClientTests}.
 * 
 * This should not be run as part of an automated test suite -- hence the name
 * ends in "s".
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/appContext-rpc-server.xml" })
public class RabbitMqServerTests {

	@Test
	public void testStartRabbitMqDummyHandler() throws InterruptedException {
		Thread.sleep(Integer.MAX_VALUE);
	}

}
