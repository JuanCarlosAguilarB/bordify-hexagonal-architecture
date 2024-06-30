package com.bordify.shared.domain;
public class EntityNotFound extends RuntimeException{
    public EntityNotFound(String message) {
        super(message);
    }
}
