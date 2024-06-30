package com.bordify.configuration.infrastructure;

public class ResourceNotCreatedException extends RuntimeException{
        public ResourceNotCreatedException(String message) {
            super(message);
        }
}
