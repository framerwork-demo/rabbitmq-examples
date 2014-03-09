package com.introfocus.examples.rabbitmq.handler;

import com.introfocus.examples.rabbitmq.message.RunRequest;
import com.introfocus.examples.rabbitmq.message.RunResponse;


public interface MessageHandler {

	RunResponse handleRequest(RunRequest request);

	void handleBackgroundRequest(RunRequest request);

}