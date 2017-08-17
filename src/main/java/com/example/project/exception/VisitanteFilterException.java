package com.example.project.exception;

public class VisitanteFilterException extends Exception {
	
	/** The error code. */
    private final Integer errorCode;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /**
     * Instantiates a new get visitante filter exception.
     *
     * @param errorCode the error code
     */
	public VisitanteFilterException(Integer code) {
		super();
		this.errorCode = code;
	}
	
	 /**
     * Instantiates a new get visitante filter exception.
     *
     * @param errorCode the error code
     * @param message the message
     * @param cause the cause
     */
	public VisitanteFilterException(Integer code, String message, Throwable cause) {
		super(message,cause);
		this.errorCode = code;
	}
	
	 /**
     * Instantiates a new get visitante filter exception.
     *
     * @param errorCode the error code
     * @param message the message
     */
	public VisitanteFilterException(Integer code,String message) {
		super(message);
		this.errorCode = code;
	}
	

	/**
	 * @return the errorCode
	 */
	public Integer getErrorCode() {
		return errorCode;
	}

}
