package com.introfocus.examples.rabbitmq.pubsub;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.introfocus.examples.rabbitmq.message.RunRequest;
import com.introfocus.examples.rabbitmq.service.MessagingService;

/**
 * 
 * Runs a publisher that sends messages to an exchange.
 * 
 * Run the {@link RabbitMqSubscriberTests} first.
 * 
 * @author Nihal
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/appContext-pubsub-publisher.xml" })
public class RabbitMqPublisherTests {

	@Value("${exchange.name}")
	private String exchange;

	private final class WorkQueueCallable implements Runnable {
		private int i;

		public WorkQueueCallable(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			RunRequest request = new RunRequest();
			request.setMessageNumber(i);
			messagingService.sendExchangeMessage(exchange, request);
		}
	}

	@Autowired
	MessagingService messagingService;

	@Test
	public void testStartDummyRabbitMqWorkQueueRequests() throws Exception {

		ExecutorService e = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5000; i++) {
			Thread.sleep(10);
			e.execute(new WorkQueueCallable(i));
		}

	}

}
