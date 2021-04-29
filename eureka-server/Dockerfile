FROM openjdk:8-jdk-alpine

RUN apk --no-cache add netcat-openbsd

ADD target/eureka-server.jar eureka-server.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${ENV}","eureka-server.jar"]