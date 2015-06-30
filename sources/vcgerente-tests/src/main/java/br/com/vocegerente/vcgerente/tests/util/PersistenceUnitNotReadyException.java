package br.com.vocegerente.vcgerente.tests.util;

@SuppressWarnings("serial")
public class PersistenceUnitNotReadyException extends RuntimeException {

	public PersistenceUnitNotReadyException() {
		super();
	}

	public PersistenceUnitNotReadyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PersistenceUnitNotReadyException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceUnitNotReadyException(String message) {
		super(message);
	}

	public PersistenceUnitNotReadyException(Throwable cause) {
		super(cause);
	}

	
}
