FROM openjdk:8-jdk-alpine
ADD target/ms-gateway.jar ms-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${ENV}","ms-gateway.jar"]