package com.introfocus.examples.rabbitmq.service.impl;

import java.nio.charset.Charset;
import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Autowired;

import com.introfocus.examples.rabbitmq.message.Request;
import com.introfocus.examples.rabbitmq.service.MessagingService;

/**
 * Default implementation of {@link MessagingService}.
 */
public class DefaultMessagingService implements MessagingService {

	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private String rpcRequestsQueue;

	private String backgroundRequestsQueue;

	@Autowired
	private RabbitOperations rabbitOperations;

	@Autowired
	private AmqpAdmin amqpAdmin;

	MessagePostProcessor correlationConfiguringMessagePostProcessor = new MessagePostProcessor() {
		public Message postProcessMessage(Message message) throws AmqpException {
			message.getMessageProperties().setCorrelationId(
					UUID.randomUUID().toString().getBytes(DEFAULT_CHARSET));
			return message;
		}
	};

	@SuppressWarnings("unchecked")
	public <T> T sendRpcRequest(Request<T> request) {
		return (T) rabbitOperations.convertSendAndReceive(rpcRequestsQueue,
				request, correlationConfiguringMessagePostProcessor);
	}

	public void sendExchangeMessage(String exchange, Object message) {
		rabbitOperations.convertAndSend(exchange, "", message);
	}

	public void sendWorkQueueRequest(Request<?> request) {
		rabbitOperations.convertAndSend(backgroundRequestsQueue, request);
	}

	public void setRpcRequestsQueue(String rpcRequestsQueue) {
		this.rpcRequestsQueue = rpcRequestsQueue;
	}

	public void setBackgroundRequestsQueue(String backgroundRequestQueue) {
		this.backgroundRequestsQueue = backgroundRequestQueue;
	}

}
