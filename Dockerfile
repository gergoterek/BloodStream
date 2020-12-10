FROM ubuntu:21.04


RUN apt update
RUN apt install -y maven

COPY pom.xml /usr/local/service/pom.xml
COPY src /usr/local/service/src

WORKDIR /usr/local/service
RUN mvn clean install
CMD ["mvn", "spring-boot:run"]

WORKDIR /usr/local/service

EXPOSE 8080



