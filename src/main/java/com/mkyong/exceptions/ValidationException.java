package com.mkyong.exceptions;

/**
 * Created by D-QU92GL on 19/02/2018.
 */
public class ValidationException extends RuntimeException  {
    private String msg;
    public ValidationException(String msg){
        super(msg);
    }
}
