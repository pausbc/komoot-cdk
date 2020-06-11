# Welcome to komoot-CDK!
This is project will provision minimal environment to run [signups-lambda](https://github.com/pausbc/signups-lambda)

## Infrastructure
Resources:
* DynamoDB table `Users` - a persisted cache to hold most recently registered users.
* Lambda function `challenge-greet-user`. Reads SNS Topic, constructs a greeting and sends it to recently registered user.
* Topic `challenge-backend-signups` as a trigger for lambda function.

## Notes 
* Make sure JAVA_HOME points to at least JDK 11 (tested with OpenJDK Corretto-11.0.7.10.1)
* Stack uses `eu-west-1` region
* CDK destroy will delete a stack with all resources and data (using RemovalPolicy.DESTROY policy on DynamoDB)

## CDK first time setup guide
* install aws-cli
* setup aws cli credentials
* install npm
* confirm installation `npm --version`
    * If strange permission errors: `sudo chown -R $USER /usr/local/lib/node_modules`
* install cdk `npm install -g aws-cdk`
* confirm installation `cdk --version`

## Stack management:
* `cdk bootstrap` (if deploy first time)
* list stacks: `cdk ls`
* deploy stack:
    * `mvn clean package`
	* `cdk deploy`
* delete stack: `cdk destroy`

Enjoy!
