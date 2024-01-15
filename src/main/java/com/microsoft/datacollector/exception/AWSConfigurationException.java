package com.microsoft.datacollector.exception;

public class AWSConfigurationException extends Exception {
    public AWSConfigurationException(String message) {
        super(message);
    }

    public AWSConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
