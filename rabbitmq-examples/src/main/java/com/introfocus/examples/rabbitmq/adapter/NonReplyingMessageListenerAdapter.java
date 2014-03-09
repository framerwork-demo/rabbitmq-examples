package com.introfocus.examples.rabbitmq.adapter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import com.rabbitmq.client.Channel;

public class NonReplyingMessageListenerAdapter extends MessageListenerAdapter {

	@Override
	protected void handleResult(Object result, Message request, Channel channel) throws Exception {
		/**
		 * Do nothing to handle the result! Do not send the result anywhere!
		 */
	}

}
