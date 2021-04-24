#!/bin/sh

while ! nc -z eureka-server-app 8761 ; do
    echo "##########################################"
    echo "######## Aguardando Eureka-server ########"
    echo "##########################################"
    sleep 20
done

java -jar -Dspring.profiles.active=${ENV} /opt/lib/ms-aluno.jar