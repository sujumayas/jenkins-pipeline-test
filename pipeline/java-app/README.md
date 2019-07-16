# Clients Microservice Spring Boot Sample Application

Just a simple Spring Boot Sample API using Swagger, Maven, and Jenkins & Docker for CI/CD pipelines. 

## Getting Started

Spring Boot Applications are too simple to run. Just Install JAVA and Maven, and use the best IDE you want to use. The application comes with a integrated Tomcat server so we do not have to worry about that. 

### Prerequisites

- JDK 1.8 or later
- (Gradle 4+ or) Maven 3.2+ (Althou you could run it with gradle, this project is not configured for that... sorry)

### Installing

If you have Java and Maven installed... 

Just: 

```bash
$ mvn spring-boot:run
```

That will run the server on the http://localhost:9001/ (*you can change the port in the `/src/main/resources/application.properties` Groovy file)

## Running the tests

**FALTA**
Explain how to run the automated tests for this system
**FALTA**

### Functional Test: 

You can review all api routes in swagger, but here are the main ones: 

- POST /api/v1/clients/create 
- GET /api/v1/clients/kpi 
- GET /api/v1/clients/list

## Deployment 

**FALTA**
Add additional notes about how to deploy this on a live system
`docker run -d -v jenkins_home:/var/jenkins_home -p 8080:8080 jenkins/jenkins:lts`
**FALTA**

## Built With

* [Spring](https://spring.io) - Spring & Spring Boot
* [Maven](https://maven.apache.org/) - Dependency Management
* [Swagger](https://swagger.io/) - Oh the docs!
* [Jenkins](https://jenkins.io/) - Pipelines
* [Docker](https://docker.com/) - Containeeeeers <3

## Contributing

No contributing allowed for this project. 

## Authors

* **Esen Espinosa** - and his new love for Spring Boot Applications - 

## Acknowledgments

- Just in case we want to acknowledge something someday. 

## TODO BackEnd SpringBoot: 

- *[DONE]* Microservicio desarrollado en JAVA Spring boot​ 
    - *[DONE]* API Rest documentada en Swagger​ 
    - Deployado en AWS o algún CLOUD + código subido en GITHUB​
    
    - **[DONE]** Endpoint de Entrada POST /api/v1/clients/create 
        - **[DONE]** Name
        - **[DONE]** LastName
        - **[DONE]** Age   *Should be birthDate dependant, we are gonna asume users are honest in their math*
        - **[DONE]** BirthDate

    - **[DONE]** Endpoint de salida GET /api/v1/clients/kpi 
        - **[DONE]** Promedio edad entre todos los clientes​
        - *[...doing]* Desviación estándar entre las edades de todos los clientes​ 
        
    - **[DONE]** Endpoint de salida GET /api/v1/clients/list
        - **[DONE]** Lista de personas con todos los datos + fecha probable de muerte de cada una​ 


## TODO DevOPS Docker&Jenkins:

- *[DONE]* Desplegar un contenedor Docker de Jenkins en una Instancia EC2 de AWS​
    
    - Crear una Pipeline de CI/CD para el microservicio del paso 1 en Jenkins​
        - *[DONE]* Job de Build con Maven y codigo en Github​
        - Job de Unit Test con Maven​
        - *[DONE]* Job de Deploy en una instancia EC2 con Docker​
        - Job de Functional Test con Jmeter o Postman​

    - Enviar links, usuarios y password por correo para poder probar:​
        - La aplicación y sus endpoints​
        - Jenkins y el pipeline

For swagger use: 
(https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg)