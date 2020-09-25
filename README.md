# BloodStream
BloodStream is a web application, which creates connection between blood donors and blood donation services to make easier and faster get to the package for the patient.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Node Package Manager 6](https://nodejs.org)


### Clone the repository

`git clone https://github.com/gergoterek/BloodStream.git` - Copy the application on your local machine

`cd BloodStream` - Enter application folder



### Install Maven dependencies and start Spring Boot app
`mvn install` - Download the necessary dependencies

`mvn spring-boot:run` - Start running the Spring Boot application

Shut it down manually with `Ctrl-C`.


### Install npm packages and run npm scripts
`npm install` - Download the necessary dependencies

`npm start`- Build the application

Shut it down manually with `Ctrl-C`.


### Tests
`mvn test`- Run all the Maven unit test classes.

`npm test` - Builds the application and runs NPM Intern tests (both unit and functional) one time.
