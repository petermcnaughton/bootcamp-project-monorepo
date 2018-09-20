package com.avocados.skyjourneys;

import com.avocados.skyjourneys.core.AWSSecretService;
import com.avocados.skyjourneys.resources.FlightDetailsResource;
import com.avocados.skyjourneys.resources.FlightSearchResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SkyJourneysApplication extends Application<SkyJourneysConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SkyJourneysApplication().run(args);
    }

    @Override
    public String getName() {
        return "Sky Journeys (core)";
    }

    @Override
    public void initialize(final Bootstrap<SkyJourneysConfiguration> bootstrap) {
        String key = AWSSecretService.getAllMylesAPIKey();
        if (key == null){
            throw new NoSuchFieldError("AllMyles API Key could not be loaded from Secrets Service");
        } else {
            System.out.println("API Key Found: " + key);
        }
    }

    @Override
    public void run(final SkyJourneysConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new FlightDetailsResource());
        environment.jersey().register(new FlightSearchResource());
    }

}
