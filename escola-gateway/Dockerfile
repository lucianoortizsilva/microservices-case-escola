FROM openjdk:8-jdk-alpine

LABEL author="lucianoortizsilva@gmail.com"

RUN apk --no-cache add netcat-openbsd

ADD target/escola-gateway.jar escola-gateway.jar

COPY target/escola-gateway.jar /opt/lib/

COPY entrypointEscolaGateway.sh /opt/bin/entrypointEscolaGateway.sh

RUN chmod 755 /opt/bin/entrypointEscolaGateway.sh

EXPOSE 8080