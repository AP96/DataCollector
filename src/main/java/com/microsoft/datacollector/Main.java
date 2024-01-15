package com.microsoft.datacollector;

import com.microsoft.datacollector.collector.EC2DataCollector;
import com.microsoft.datacollector.configuration.AWSClientBuilder;
import com.microsoft.datacollector.evaluator.EC2InstanceComplianceChecker;
import com.microsoft.datacollector.handler.ExceptionHandler;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        setupLogger();

        try {
            EC2DataCollector ec2DataCollector = new EC2DataCollector(AWSClientBuilder.ec2ClientBuilder());
            EC2InstanceComplianceChecker ec2InstanceComplianceChecker = new EC2InstanceComplianceChecker();
            DescribeInstancesResponse describeInstanceResponse = ec2DataCollector.collectEC2Data();

            List<Instance> healthyInstances = new ArrayList<>();
            List<Instance> unhealthyInstances = new ArrayList<>();

            collectInstanceData(describeInstanceResponse, ec2InstanceComplianceChecker, healthyInstances, unhealthyInstances);

            printEC2InstanceDetails("Healthy EC2 Instances", healthyInstances);
            printEC2InstanceDetails("Unhealthy EC2 Instances", unhealthyInstances);

        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    private static void setupLogger() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    private static void collectInstanceData(DescribeInstancesResponse describeInstanceResponse,
                                            EC2InstanceComplianceChecker ec2InstanceComplianceChecker,
                                            List<Instance> healthyInstances, List<Instance> unhealthyInstances) {
        for (Reservation reservation : describeInstanceResponse.reservations()) {
            for (Instance instance : reservation.instances()) {
                if (ec2InstanceComplianceChecker.isCompliant(instance)) {
                    healthyInstances.add(instance);
                } else {
                    unhealthyInstances.add(instance);
                }
            }
        }
    }

    private static void printEC2InstanceDetails(String title, List<Instance> instances) {
        if (!instances.isEmpty()) {
            System.out.println("\n\n------------------ " + title + " ------------------");
            for (Instance instance : instances) {
                System.out.println("\nInstance ID: " + instance.instanceId());
                System.out.println("Instance Tags:");
                instance.tags().forEach(tag -> {
                    System.out.println("Key: " + tag.key());
                    System.out.println("Value: " + tag.value());
                });
            }
            System.out.println();
        }
    }
}
