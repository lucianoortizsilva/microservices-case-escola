FROM openjdk:8-jdk-alpine

LABEL author="lucianoortizsilva@gmail.com"

RUN apk --no-cache add netcat-openbsd

ADD target/ms-professor.jar ms-professor.jar 

COPY target/ms-professor.jar /opt/lib/

COPY entrypointProfessor.sh /opt/bin/entrypointProfessor.sh

RUN chmod 755 /opt/bin/entrypointProfessor.sh

EXPOSE 8083