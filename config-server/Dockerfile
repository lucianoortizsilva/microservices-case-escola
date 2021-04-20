FROM openjdk:latest 
VOLUME /tmp
EXPOSE 8761 
ADD /target/eureka-server.jar eureka-server.jar 
ENTRYPOINT ["java","-jar","eureka-server.jar"]