Data Collector Application
The EC2 Data Collector Application is a Java-based backend application designed to collect information about AWS EC2 instances
and evaluate their compliance based on specified criteria. It uses the AWS SDK for Java to interact with AWS EC2 services,
retrieve instance details, and check compliance.

Components

The application is divided into several components for modularity and maintainability:

Project Structure

DataCollector
├── .idea
├── src
│   └── main
│       └── java
│           └── datacollector
│               ├── collector
│               │   └── EC2DataCollector.java
│               ├── configuration
│               │   └── AWSClientBuilder.java
│               ├── constants
│               │   └── InstanceValues.java
│               ├── evaluator
│               │   └── EC2InstanceComplianceChecker.java
│               ├── exception
│               │   ├── AWSConfigurationException.java
│               │   └── DataCollectException.java
│               └── handler
│                   └── ExceptionHandler.java
│               └── Main.java
├── resources
├── test
├── target
├── .gitignore
├── pom.xml
└── README

1. EC2DataCollector

The EC2DataCollector class represents the collection of data from AWS EC2 instances.
It uses an Ec2Client instance for making API requests to AWS EC2.
The collectEC2Data method fetches information about all running EC2 instances and returns a DescribeInstancesResponse.

2. AWSClientBuilder

The AWSClientBuilder class is responsible for creating an Ec2Client instance with the needed AWS region
and credentials configuration. By default, it uses the AWS SDK with default credentials and the US_EAST_1 region

3. EC2InstanceComplianceChecker

The EC2InstanceComplianceChecker class tests the compliance of an EC2 instance based on specified tags.
The isCompliant method takes an Instance object as input and returns a boolean indicating compliance status.
If an instance has a tag with a specific key "compliance" with "COMPLIANT" value, it is considered compliant.

4. Exception Handling

Categorizes exceptions into AWSConfigurationException, DataCollectionException, and generic exceptions based on their types.
Specific error messages and logging levels are used to provide detailed information about the errors.
This class is extendable to handle more exception types as needed.

5. Main Class

The Main class serves as the entry point for the application.
It contains the workflow by initializing the necessary components, collecting EC2 instance data, evaluating compliance/non-compliance, and printing the results.
It also sets up a logger to capture important log messages.

Additional Files in Repository:
(1) Screenshot of one created AWS Instance machine with found COMPLIANT status + Healthy Compliant EC2 Instance - printing ID
(2) Screenshot of Multiple EC2 Instances (Compliant & Non-Compliant) with printed details + Screenshot of AWS Launched Multiple Instances
