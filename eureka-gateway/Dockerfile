FROM openjdk:latest 
ADD target/eureka-gateway.jar eureka-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","eureka-gateway.jar"]