FROM openjdk:8-jdk-alpine

LABEL author="lucianoortizsilva@gmail.com"

RUN apk --no-cache add netcat-openbsd

ADD target/ms-avaliacao.jar ms-avaliacao.jar 

COPY target/ms-avaliacao.jar /opt/lib/

COPY entrypointAvaliacao.sh /opt/bin/entrypointAvaliacao.sh

RUN chmod 755 /opt/bin/entrypointAvaliacao.sh

EXPOSE 8082