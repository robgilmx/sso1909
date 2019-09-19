FROM openjdk:8-jdk
MAINTAINER Roberto Gil (robgilmx@gmail.com)
ENV datasource_url=jdbc:postgresql://localhost:5432/ksso
ENV datasource_usr=ssomng
ENV datasource_psw=@qr3&wrNXNU!J7P(
ENV datasource_ptf=postgres
ENV server_port=8080
RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml /usr/local/ksso1909/pom.xml
COPY src /usr/local/ksso1909/src
COPY readme.md /usr/local/ksso1909/readme.md
WORKDIR /usr/local/ksso1909
RUN mvn clean package
ENTRYPOINT ["mvn", "spring-boot:run","-Dspring-boot.run.arguments=\"--server.port=${server_port},--spring.datasource.url=${datasource_url}, --spring.datasource.username=${datasource_usr},  --spring.datasource.password=${datasource_psw},  --spring.datasource.platform=${datasource_ptf}\""]
