## General info
This project is a simple code challenge excercise with the REST endpoints for the domains Enrollees and Dependents.
	
## Technologies
Project is created with:
* Spring Boot 2.3.3.RELEASE
	
## Setup
To run this project,
```
$ mvn clean install
$ mvn spring-boot:run
```
## Entities
1) Enrollee Entity
2) Enrolle Dependent Entitiy

## Database 
H2 Database with SpringBoot 

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

## API validation
The API validation can be done through Postman.
