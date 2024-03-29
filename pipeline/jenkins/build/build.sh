#!/bin/bash

# Copy the new jar to the build location
cp -f pipeline/java-app/target/*.jar pipeline/jenkins/build/

echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

cd pipeline/jenkins/build/ && docker-compose -f docker-compose-build.yml build --no-cache
