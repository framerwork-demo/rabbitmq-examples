package com.introfocus.examples.rabbitmq.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import com.introfocus.examples.rabbitmq.handler.MessageHandler;
import com.introfocus.examples.rabbitmq.message.RunRequest;
import com.introfocus.examples.rabbitmq.message.RunResponse;

/**
 * Messages are delegated to this class via a {@link MessageListenerAdapter} in
 * the Spring XML configuration.
 * 
 * @author Nihal
 * 
 */
public class DefaultMessageHandler implements MessageHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(DefaultMessageHandler.class);

	@Override
	public RunResponse handleRequest(RunRequest request) {
		return internalHandleRequest(request);
	}

	public void handleBackgroundRequest(RunRequest request) {
		internalHandleRequest(request);
	}

	RunResponse internalHandleRequest(RunRequest request) {
		LOG.info("Handling message " + request);
		try {
			Thread.sleep(100);

			// If exceptions are thrown, messages should be re-queued
			// throw new RuntimeException();
		} catch (InterruptedException e) {
			// Don't worry about being interrupted
		}

		RunResponse response = new RunResponse();
		response.setMessageNumber(request.getMessageNumber());
		return response;
	}

}
