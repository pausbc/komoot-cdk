package com.myorg;

import software.amazon.awscdk.core.App;

import java.util.Arrays;

public class KomootCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        new KomootCdkStack(app, "KomootCdkStack");

        app.synth();
    }
}
