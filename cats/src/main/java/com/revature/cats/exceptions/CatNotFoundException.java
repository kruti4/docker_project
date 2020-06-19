package com.revature.cats.exceptions;

public class CatNotFoundException extends RuntimeException {

	public CatNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CatNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CatNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CatNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CatNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
