FROM openjdk:8-jdk-alpine
LABEL author="lucianoortizsilva@gmail.com"
RUN apk --no-cache add netcat-openbsd
ADD target/ms-boletim.jar ms-boletim.jar 
COPY target/ms-boletim.jar /opt/lib/
COPY entrypointBoletim.sh /opt/bin/entrypointBoletim.sh
RUN chmod 755 /opt/bin/entrypointBoletim.sh
EXPOSE 8082