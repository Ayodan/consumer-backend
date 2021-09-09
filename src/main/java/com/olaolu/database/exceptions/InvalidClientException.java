package com.olaolu.database.exceptions;

/**
 * @author akano.olanrewaju  @on 24/03/2021
 */
public class InvalidClientException extends RuntimeException {
    public InvalidClientException(String message){
        super(message);
    }
}
