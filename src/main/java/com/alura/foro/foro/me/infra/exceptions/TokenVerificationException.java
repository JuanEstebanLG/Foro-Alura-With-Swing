package com.alura.foro.foro.me.infra.exceptions;

public class TokenVerificationException extends Exception {

    public TokenVerificationException(String message) {
        super(message);
    }
}
