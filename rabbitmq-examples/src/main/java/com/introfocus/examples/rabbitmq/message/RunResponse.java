package com.introfocus.examples.rabbitmq.message;

import java.io.Serializable;

public class RunResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private int messageNumber;

	public int getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}

}
