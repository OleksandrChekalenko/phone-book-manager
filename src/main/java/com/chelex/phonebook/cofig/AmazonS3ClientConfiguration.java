package com.chelex.phonebook.cofig;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.services.s3.AmazonS3;

@Configuration
public class AmazonS3ClientConfiguration {

    @Value("${aws.credentials.accessKey}")
    private String awsId;

    @Value("${aws.credentials.secretKey}")
    private String awsKey;

    @Value("${aws.region.static}")
    private String region;

    @Bean
    public AmazonS3 s3client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, awsKey);

        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
