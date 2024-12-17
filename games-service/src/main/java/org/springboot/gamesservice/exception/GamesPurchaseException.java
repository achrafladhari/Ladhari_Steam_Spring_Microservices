package org.springboot.gamesservice.exception;

public class GamesPurchaseException extends RuntimeException{
    public GamesPurchaseException(String s) {
        super(s);
    }
}
