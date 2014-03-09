package com.introfocus.examples.rabbitmq.message;

import java.io.Serializable;

/**
 * Marker interface. A request is an object that encapsulates the work to be
 * done.
 * 
 * @author Nihal
 * 
 * @param <R>
 *            the response type associated with this request.
 */
public interface Request<R> extends Serializable {

}
