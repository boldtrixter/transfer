/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.exception;

/**
 *
 * @author Dmitry
 */
public class ServerException extends Exception{
    
    private String ref;

    public ServerException() {
        ref = new String();
    }

    public ServerException(String message, String ref) {
        super(message);
        this.ref = ref;
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

    public String getRef() {
        return ref;
    }
    
}