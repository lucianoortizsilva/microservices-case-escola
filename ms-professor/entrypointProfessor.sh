#!/bin/sh

while ! nc -z eureka-server-app 8761 ; do
    echo "##########################################"
    echo "######## Aguardando Eureka-server ########"
    echo "##########################################"
    sleep 40
done

java -jar -Dspring.profiles.active=${ENV} /opt/lib/ms-professor.jar