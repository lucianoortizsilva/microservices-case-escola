FROM openjdk:8-jdk-alpine
ADD target/ms-aluno.jar ms-aluno.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${ENV}","ms-aluno.jar"]