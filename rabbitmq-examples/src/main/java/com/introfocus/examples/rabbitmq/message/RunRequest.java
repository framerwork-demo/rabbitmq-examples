package com.introfocus.examples.rabbitmq.message;



public class RunRequest implements Request<RunResponse> {

	private static final long serialVersionUID = 1L;

	private int messageNumber;

	public int getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}

}
