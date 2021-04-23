FROM openjdk:8-jdk-alpine
LABEL author="lucianoortizsilva@gmail.com"
RUN apk --no-cache add netcat-openbsd
ADD target/ms-gateway.jar ms-gateway.jar
COPY target/ms-gateway.jar /opt/lib/
COPY entrypointGateway.sh /opt/bin/entrypointGateway.sh
RUN chmod 755 /opt/bin/entrypointGateway.sh
EXPOSE 8080