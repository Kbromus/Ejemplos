package com.example.project.model;

import lombok.Data;

/**
 * The Class Error.
 */

/**
 * Clase de mapeo de excepciones
 * @author cmata
 *
 */
public class Error {

    /** The http code. */
    private Integer httpCode;

    /** The http message. */
    private String httpMessage;

    /**
	 * @return the httpCode
	 */
	public Integer getHttpCode() {
		return httpCode;
	}

	/**
	 * @param httpCode the httpCode to set
	 */
	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}

	/**
	 * @return the httpMessage
	 */
	public String getHttpMessage() {
		return httpMessage;
	}

	/**
	 * @param httpMessage the httpMessage to set
	 */
	public void setHttpMessage(String httpMessage) {
		this.httpMessage = httpMessage;
	}

	/**
     * Instantiates a new error.
     *
     * @param httpCode the http code
     */
    public Error(Integer httpCode) {
        super();
        this.httpCode = httpCode;
    }

    /**
     * Instantiates a new error.
     *
     * @param httpCode the http code
     * @param httpMessage the http message
     */
    public Error(Integer httpCode, String httpMessage) {
        super();
        this.httpCode = httpCode;
        this.httpMessage = httpMessage;
    }

    /**
     * Instantiates a new error.
     */
    public Error() {
        super();
    }

}
