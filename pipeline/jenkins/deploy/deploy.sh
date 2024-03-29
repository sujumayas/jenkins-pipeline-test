#!/bin/bash

echo maven-automated-pipeline > /tmp/.auth
echo $BUILD_TAG >> /tmp/.auth
echo $PASS >> /tmp/.auth

echo "****************************************"
echo "***** Deploying to ec2 3rd server :D ***"
echo "****************************************"

scp -i /opt/prod /tmp/.auth prod-user@ec2-54-175-13-162.compute-1.amazonaws.com:/tmp/.auth
scp -i /opt/prod ./pipeline/jenkins/deploy/publish prod-user@ec2-54-175-13-162.compute-1.amazonaws.com:/tmp/publish
ssh -i /opt/prod prod-user@ec2-54-175-13-162.compute-1.amazonaws.com "/tmp/publish"
