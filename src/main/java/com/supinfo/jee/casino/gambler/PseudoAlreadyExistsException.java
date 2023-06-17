package com.supinfo.jee.casino.gambler;

public class PseudoAlreadyExistsException extends Throwable {
    public PseudoAlreadyExistsException(String pseudo) {
        super("Pseudo " + pseudo + " already exists !");
    }
}
