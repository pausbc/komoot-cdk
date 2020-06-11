package com.pausub.challenge.signups;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

public class KomootCdkApp {

    public static void main(final String[] args) {
        App app = new App();
        Environment euWest1 = Environment.builder().region("eu-west-1").build();
        new KomootCdkStack(app, "KomootCdkStack", StackProps.builder().env(euWest1).build());
        app.synth();
    }
}

