FROM openjdk:18-jdk-alpine3.14
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]