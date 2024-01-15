package com.microsoft.datacollector.collector;

import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;

public class EC2DataCollector {

    private final Ec2Client ec2Client;

    public EC2DataCollector(Ec2Client ec2Client) {
        this.ec2Client = ec2Client;
    }

    public DescribeInstancesResponse collectEC2Data() {
        return ec2Client.describeInstances();
    }
}
