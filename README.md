# TENPO Backend Challenge

![](https://img.shields.io/badge/build-success-brightgreen.svg)

![](https://img.shields.io/badge/java_8-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/postgrestsql-✓-blue.svg)
![](https://img.shields.io/badge/jwt-✓-blue.svg)
![](https://img.shields.io/badge/swagger_2-✓-blue.svg)
![](https://img.shields.io/badge/maven-✓-blue.svg)
![](https://img.shields.io/badge/docker-✓-blue.svg)

-------------------
## postgresql DB

This application uses postgresql for database. The DB will be dropped and created in each deploy of the application.

-------------------

## How to run this API locally

Java 8, Maven and Docker are needed. If you have to install them, please visit the following links:

      -https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
      -https://maven.apache.org
      -https://docs.docker.com

Fork and clone the repository
```
   git clone git@github.com:joacogalindez/tenpo_challenge.git
```
Install Maven dependencies 
```
  mvn install
```
In the project folder, run the project on docker containers using docker-compose. It will pull and run requiered images from dockerHub (make sure port 8080 is not in use)
```
  docker-compose up
```
