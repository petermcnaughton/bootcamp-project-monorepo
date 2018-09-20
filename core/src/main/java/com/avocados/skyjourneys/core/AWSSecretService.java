package com.avocados.skyjourneys.core;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import io.dropwizard.jackson.Jackson;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class AWSSecretService {

    private static final String allMylesAPIKey = getAllMylesAPISecret();

    public static String getAllMylesAPIKey(){
        return allMylesAPIKey;
    }

    private static String getAllMylesAPISecret() {

        String secretName = "AllMyles_API_Key";
        String endpoint = "secretsmanager.eu-west-1.amazonaws.com";
        String region = "eu-west-1";

        AwsClientBuilder.EndpointConfiguration config = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
        AWSSecretsManagerClientBuilder clientBuilder = AWSSecretsManagerClientBuilder.standard();
        clientBuilder.setEndpointConfiguration(config);
        AWSSecretsManager client = clientBuilder.build();


        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;
        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);

        } catch(ResourceNotFoundException e) {
            System.out.println("The requested secret " + secretName + " was not found");
        } catch (InvalidRequestException e) {
            System.out.println("The request was invalid due to: " + e.getMessage());
        } catch (InvalidParameterException e) {
            System.out.println("The request had invalid params: " + e.getMessage());
        }

        if(getSecretValueResult == null) {
            return null;
        }

        // Decrypted secret using the associated KMS CMK
        if(getSecretValueResult.getSecretString() != null) {
            try {
                return Jackson.newObjectMapper()
                        .readTree(getSecretValueResult.getSecretString())
                        .get("AllMyles_API_Key").textValue();
            } catch (IOException e) {
                throw new IndexOutOfBoundsException();
            }
        }

        throw new RuntimeException();
    }

}
