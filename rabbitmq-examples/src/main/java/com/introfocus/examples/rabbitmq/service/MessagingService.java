package com.introfocus.examples.rabbitmq.service;

import com.introfocus.examples.rabbitmq.message.Request;

/**
 * 
 * Interface to the service that sends messages via RabbitMQ.
 * 
 */
public interface MessagingService {

	<T> T sendRpcRequest(Request<T> request);

	void sendWorkQueueRequest(Request<?> request);

	void sendExchangeMessage(String exchange, Object message);
}
