#!/bin/sh

while ! nc -z eureka-server-app 8761 ; do
    echo "##########################################"
    echo "######## Aguardando Eureka-server ########"
    echo "##########################################"
    sleep 45
done

java -jar -Dspring.profiles.active=${ENV} /opt/lib/ms-boletim.jar