#!/bin/sh

while ! nc -z eureka-server-app 8761 ; do
    echo "##########################################"
    echo "######## Aguardando Eureka-server ########"
    echo "##########################################"
    sleep 60
done

while ! nc -z zipkin-server-app 9411 ; do
    echo "##########################################"
    echo "######## Aguardando zipkin-server ########"
    echo "##########################################"
    sleep 60
done

java -jar -Dspring.profiles.active=${ENV} /opt/lib/escola-gateway.jar