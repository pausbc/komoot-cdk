# Welcome to komoot-CDK!
This is project will provision minimal environment to run signups-lambda

## Infrastructure
Resources:
* Dynamo db table `Users` - a persisted cache to hold most recently registered users.
* Lambda function `challenge-greet-user`. Reads SNS Topic, constructs a greeting and sends it to recently registered user.
* Topic `challenge-backend-signups` as a trigger for lambda function.

## Notes 
* Deployed to eu-west-1 region
* Cdk destroy Will delete a stack with all resources and data (using RemovalPolicy.DESTROY policy on dynamodb)

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
