package com.supinfo.jee.casino.gambler;

public class EmptyPasswordException extends RuntimeException {
    public EmptyPasswordException() {
        super("Password attribute should have a valid value !");
    }

}
