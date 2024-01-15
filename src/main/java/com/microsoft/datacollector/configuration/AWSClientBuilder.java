package com.microsoft.datacollector.configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

/** AWS Configuration Class
 *  Initialize the AWS SDK with my default credentials and region.
 */
public class AWSClientBuilder {

    public static Ec2Client ec2ClientBuilder() {
        return Ec2Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(DefaultCredentialsProvider.builder()
                        .profileName("default")
                        .build())
                .build();
    }

}
