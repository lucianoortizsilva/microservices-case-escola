FROM openjdk:8-jdk-alpine
ADD /target/ms-boletim.jar ms-boletim.jar 
EXPOSE 8082 
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${ENV}","ms-boletim.jar"]