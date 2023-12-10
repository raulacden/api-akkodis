package com.rsp.akkodis.api.prices.domain.error;

public class DomainException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DomainException(final String message) {
		super(message);
	}
}
