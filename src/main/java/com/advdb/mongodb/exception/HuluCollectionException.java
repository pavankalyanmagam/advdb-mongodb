package com.advdb.mongodb.exception;

public  class HuluCollectionException extends RuntimeException {
    public HuluCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Hulu Show with title "+id+" not found!";
    }

    public static String HuluAlreadyExists() {
        return "Hulu Show with given name already exists";
    }
}