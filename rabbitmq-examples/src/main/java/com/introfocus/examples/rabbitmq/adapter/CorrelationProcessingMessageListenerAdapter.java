package com.introfocus.examples.rabbitmq.adapter;

import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

public class CorrelationProcessingMessageListenerAdapter extends MessageListenerAdapter {

	@Override
	protected void postProcessResponse(Message request, Message response) throws Exception {

		/*
		 * Add the stacked correlation header so rabbitmq client can correlate
		 * requests when using a single request queue. This is not a problem if
		 * the client uses a temporary request queue, but this is inefficient,
		 * so when using one queue, we must set the stacked correlation header.
		 * 
		 * If this code is not present, RabbitMqClientTests and
		 * RabbitMqServerTests will fail.
		 * 
		 */
		// Use this if using spring-rabbit 1.1.X
//		response.getMessageProperties()
//				.getHeaders()
//				.put(RabbitTemplate.STACKED_CORRELATION_HEADER,
//						request.getMessageProperties().getHeaders().get(RabbitTemplate.STACKED_CORRELATION_HEADER));

		// Use this if using spring-rabbit 1.2.x
		response.getMessageProperties().setCorrelationId(request.getMessageProperties().getCorrelationId());
		
		super.postProcessResponse(request, response);
	}

}
