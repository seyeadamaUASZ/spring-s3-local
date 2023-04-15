package com.sid.gl.springs3local.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class AppConfiguration {
    private static final String SQS_URI="http://localhost:4566";
    private static final String REGION="us-west-1";

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @PostConstruct
    public void setSystemProperty(){
        SystemPropertiesCredentialsProvider systemPropertiesCredentialsProvider=new SystemPropertiesCredentialsProvider();

        System.setProperty("aws.accessKeyId",accessKey);
        System.setProperty("aws.secretAccessKey",secretKey);
    }

    @Bean
    public AmazonS3 getAmazonS3(){

        AmazonS3 s3=
                AmazonS3ClientBuilder
                        .standard()
                        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(SQS_URI,REGION))
                        //.withCredentials(new DefaultAWSCredentialsProviderChain())
                        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                        .withPathStyleAccessEnabled(true)
                        .build();
        return s3;
    }


}
