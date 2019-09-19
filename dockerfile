FROM openjdk:8-jdk
MAINTAINER Roberto Gil (robgilmx@gmail.com)
RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml /usr/local/ksso1909/pom.xml
COPY src /usr/local/ksso1909/src
COPY readme.md /usr/local/ksso1909/readme.md
WORKDIR /usr/local/ksso1909
RUN mvn clean package
