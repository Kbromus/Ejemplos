package com.example.project.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class BadParametersResponse.
 */

@Data
@EqualsAndHashCode(callSuper=false)
/**
 * Clase de mapeo de excepciones
 * @author cmata
 *
 */
public class BadParametersResponse extends Error {

    /** The errors. */
    private List<String> moreInformation;
    
    /**
     * Instantiates a new error response.
     *
     * @param httpCode the http code
     * @param httpMessage the http message
     */
    public BadParametersResponse(Integer httpCode, String httpMessage) {
        super(httpCode, httpMessage);
    }
    
    /**
     * Instantiates a new bad parameters response.
     */
    public BadParametersResponse(){
        super();
    }
    
    

}
