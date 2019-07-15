#!/bin/bash

echo "********************"
echo "** Pushing image ***"
echo "********************"

IMAGE="maven-automated-pipeline"

echo "** Logging in ***"
docker login -u sujumayas -p $PASS
echo "*** Tagging image ***"
docker tag $IMAGE:$BUILD_TAG sujumayas/$IMAGE:$BUILD_TAG
echo "*** Pushing image ***"
docker push sujumayas/$IMAGE:$BUILD_TAG
