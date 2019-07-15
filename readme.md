# Jenkins pipeline CI/CD Spring Application + Docker <3






```sh
docker run --rm  -v  /home/ec2-user/jenkins-pipeline-test/workspace/pipeline-docker-maven/pipeline/java-app:/app -v /root/.m2/:/root/.m2/ -w /app maven:3-alpine 
```