package com.microsoft.datacollector.exception;

public class DataCollectException extends Exception {
    public DataCollectException(String message) {
        super(message);
    }

    public DataCollectException(String message, Throwable cause) {
        super(message, cause);
    }
}
