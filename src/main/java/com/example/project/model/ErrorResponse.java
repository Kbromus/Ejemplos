package com.example.project.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
/**
 * Clase de mapeo de excepciones
 * @author cmata
 *
 */
public class ErrorResponse extends Error{


    /** The more information. */
    private String moreInformation;

    /**
     * Instantiates a new error response.
     *
     * @param httpCode the http code
     * @param moreInformation the more information
     */
    public ErrorResponse(Integer httpCode, String moreInformation) {
        super(httpCode);
        this.moreInformation = moreInformation;
    }
    
    
    /**
     * Instantiates a new error response.
     *
     * @param httpCode the http code
     * @param httpMessage the http message
     * @param moreInformation the more information
     */
    public ErrorResponse(Integer httpCode, String httpMessage, String moreInformation) {
        super(httpCode, httpMessage);
        this.moreInformation = moreInformation;
    }
    

}
