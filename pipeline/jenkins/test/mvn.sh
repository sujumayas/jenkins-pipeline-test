#!/bin/bash

echo "***************************"
echo "** Testing the code ***********"
echo "***************************"
WORKSPACE=/home/ec2-user/jenkins-pipeline-test/jenkins_home/workspace/pipeline-docker-maven/pipeline

docker run --rm  -v  $WORKSPACE/java-app:/app -v /root/.m2/:/root/.m2/ -w /app maven:3-alpine "$@"

# ibmjava:8-sdk-alpine