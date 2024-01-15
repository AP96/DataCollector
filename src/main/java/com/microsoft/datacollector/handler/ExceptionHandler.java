package com.microsoft.datacollector.handler;

import com.microsoft.datacollector.exception.AWSConfigurationException;
import com.microsoft.datacollector.exception.DataCollectException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionHandler {
    private static final Logger logger = Logger.getLogger(ExceptionHandler.class.getName());

    public static void handleException(Exception e) {
        if (e instanceof AWSConfigurationException) {
            handleAwsConfigurationException((AWSConfigurationException) e);
        } else if (e instanceof DataCollectException) {
            handleDataCollectionException((DataCollectException) e);
        } else {
            handleGenericException(e);
        }
    }

    private static void handleAwsConfigurationException(AWSConfigurationException e) {
        logger.log(Level.SEVERE, "AWS Configuration Error: " + e.getMessage(), e);
    }

    private static void handleDataCollectionException(DataCollectException e) {
        logger.log(Level.SEVERE, "Data Collection Error: " + e.getMessage(), e);
    }

    private static void handleGenericException(Exception e) {
        logger.log(Level.SEVERE, "An unexpected error occurred: " + e.getMessage(), e);
    }
}
