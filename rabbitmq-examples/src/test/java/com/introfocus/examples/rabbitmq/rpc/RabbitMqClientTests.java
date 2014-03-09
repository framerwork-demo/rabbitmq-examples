package com.introfocus.examples.rabbitmq.rpc;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.introfocus.examples.rabbitmq.message.RunRequest;
import com.introfocus.examples.rabbitmq.message.RunResponse;
import com.introfocus.examples.rabbitmq.service.MessagingService;

/**
 * 
 * The purpose of this test is simply to test RPC request/response correlation
 * and handling with Rabbit MQ.
 * 
 * Run the {@link RabbitMqServerTests} first.
 * 
 * This should not be run as part of an automated test suite -- hence the name
 * ends in "s".
 * 
 * @author Nihal
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/appContext-rpc-client.xml" })
public class RabbitMqClientTests {

	private static final Logger LOG = LoggerFactory
			.getLogger(RabbitMqClientTests.class);

	private final class RpcCallable implements Callable<RunResponse> {
		private int i;

		public RpcCallable(int i) {
			this.i = i;
		}

		@Override
		public RunResponse call() throws Exception {
			RunRequest request = new RunRequest();
			request.setMessageNumber(i);
			RunResponse response = messagingService.sendRpcRequest(request);
			assertNotNull(response);
			LOG.info("Got response " + response.getMessageNumber());
			return response;
		}
	}

	private final class WorkQueueCallable implements Runnable {
		private int i;

		public WorkQueueCallable(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			RunRequest request = new RunRequest();
			request.setMessageNumber(i);
			messagingService.sendWorkQueueRequest(request);
		}
	}

	@Autowired
	MessagingService messagingService;

	@Test
	public void testStartDummyRabbitMqWorkQueueRequests() throws Exception {
		ExecutorService e = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 5000; i++) {
			e.execute(new WorkQueueCallable(i));
		}

		e.shutdown();
	}

	@Test
	public void testStartDummyRabbitMqRpcRequests() throws Exception {

		List<Future<RunResponse>> futures = new ArrayList<Future<RunResponse>>();

		ExecutorService e = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 5000; i++) {
			Future<RunResponse> future = e.submit(new RpcCallable(i));
			futures.add(future);
		}

		for (Future<RunResponse> future : futures) {
			RunResponse response = future.get();
			assertNotNull(response);
		}
	}

}
