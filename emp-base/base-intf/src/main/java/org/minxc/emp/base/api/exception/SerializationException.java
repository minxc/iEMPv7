package org.minxc.emp.base.api.exception;

public class SerializationException extends RuntimeException {
	private static final long serialVersionUID = 8847845622247770262L;

	public SerializationException(String msg) {
	        super(msg);
	    }

	    public SerializationException(String msg, Throwable throwable) {
	        super(msg, throwable);
	    }

	    public SerializationException(Throwable throwable) {
	        super(throwable);
	    }
	

}
