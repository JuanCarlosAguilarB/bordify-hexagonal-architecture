package com.bordify.shared.domain;

public class ResourceNotCreatedException extends RuntimeException{
        public ResourceNotCreatedException(String message) {
            super(message);
        }
}
