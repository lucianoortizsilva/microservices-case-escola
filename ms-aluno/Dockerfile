FROM openjdk:8-jdk-alpine

LABEL author="lucianoortizsilva@gmail.com"

RUN apk --no-cache add netcat-openbsd

ADD target/ms-aluno.jar ms-aluno.jar

COPY target/ms-aluno.jar /opt/lib/

COPY entrypointAluno.sh /opt/bin/entrypointAluno.sh

RUN chmod 755 /opt/bin/entrypointAluno.sh

EXPOSE 8081