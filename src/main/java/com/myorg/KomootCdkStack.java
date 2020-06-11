package com.myorg;

import software.amazon.awscdk.core.*;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.dynamodb.TableProps;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.lambda.eventsources.SnsEventSource;
import software.amazon.awscdk.services.sns.ITopic;
import software.amazon.awscdk.services.sns.Topic;

public class KomootCdkStack extends Stack {

    public KomootCdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Attribute partitionKey = Attribute.builder()
                .name("id")
                .type(AttributeType.NUMBER)
                .build();

        TableProps tableProps = TableProps.builder()
                .tableName("Users")
                .partitionKey(partitionKey)
                .removalPolicy(RemovalPolicy.DESTROY)
                .build();

        Table usersTable = new Table(this, "users", tableProps);

        FunctionProps functionProps = FunctionProps.builder()
                .code(Code.fromAsset("./asset/signups-lambda-1.0.jar"))
                .handler("com.pausub.challenge.signups.SignupRequestHandler::handleRequest")
                .runtime(Runtime.JAVA_11)
                .timeout(Duration.minutes(2))
                .memorySize(256)
                .build();

        Function signupHandlerLambda = new Function(this, "challenge-greet-user-test", functionProps);
        usersTable.grantFullAccess(signupHandlerLambda);

        ITopic signupTopic = Topic.fromTopicArn(this, "challenge-backend-signups", "arn:aws:sns:eu-west-1:963797398573:challenge-backend-signups");
        signupHandlerLambda.addEventSource(new SnsEventSource(signupTopic));
    }
}
