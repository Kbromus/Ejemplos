package com.example.project.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.project.model.BadParametersResponse;
import com.example.project.model.ErrorResponse;


/**
 * Manejador de excepciones para controladores del microservicio
 */
@ControllerAdvice
public class VisitanteFilterExceptionHandler extends ResponseEntityExceptionHandler {
	
	/** The messages. */
    @Autowired
    private MessageSource messages;

    /**
     * Exception handler.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(value = { VisitanteFilterException.class })
    /**
     * Manejo de errores 404
     * @param ex
     * @return
     */
    public ResponseEntity<Object> exceptionHandler(VisitanteFilterException ex) {

        Integer errorCode = ex.getErrorCode();

        if (errorCode != 400) {
            ErrorResponse error = new ErrorResponse(errorCode, ex.getMessage());

            if (errorCode == 404) {
                error.setHttpMessage(messages.getMessage("error.404.message", null, LocaleContextHolder.getLocale()));
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            
        } else {
        	BadParametersResponse error = new BadParametersResponse();
            error.setHttpCode(HttpStatus.BAD_REQUEST.value());
            error.setHttpMessage(messages.getMessage("error.400.message", null, LocaleContextHolder.getLocale()));

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
		return null;
    }

   
    @ExceptionHandler(value = { Exception.class })
    /**
     * manejo de errores 500
     * @param exception
     * @return
     */
    public ResponseEntity<ErrorResponse> handle(Exception exception) {
    	

        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                messages.getMessage("error.500.message", null, LocaleContextHolder.getLocale()),
                exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
