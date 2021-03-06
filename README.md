### Tech Stack
- Java 8+
- Spring Boot
- [Zipkin](https://zipkin.io/)
- Docker

### O que é ?
Um simples caso de uso, que representa uma escola qualquer. \
Para isso foi utilizado conceito de micro-serviços, onde pequenos serviços registram-se em um eureka-server.\
Os serviços criados:
 - escola-gateway (server como ponto de entrada, o único serviço que os clients devem conhecer;
 - ms-aluno (Micro serviço que armazena dados de aluno e, gera informações do boletim do aluno);
 - ms-avaliacao (Micro serviço que armazena dados de avaliação de cada aluno, conforme input dos professores);
 - ms-professor (Micro serviço que armazena dados dos professores);

### Comunicação ? 
A comunicação entre os serviços dependentes ocorrem via REST, usando [@FeignClient](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html).\
No exemplo implementado, um client deseja acessar o boletim de um teterminado aluno, onde:
 - Todos os serviços se registram no eureka server;
 - O Client acessa a escola-gateway, que por sua vez acessa o ms-aluno;
 - Para montar o boletim, o ms-aluno acessa as suas 2 dependências: ms-professor e ms-avaliacao;  
Todos os serviços foram configurados com Dockerfile. 
Além disso, na raiz no projeto foi criado um arquivo docker-compose.yml, para facilitar build e deploy da aplicação.

### Arquitetura
![](https://github.com/lucianoortizsilva/microservices-case-escola/blob/main/static/github/img-arquitetura.png?raw=true)

### Como rodar ?
- Realize o build em todos os projeto, executando **`mvn clean package`**
- Na raiz do projeto, execute **`docker-compose up`**, para criar as imagens e realizar o deploy

### Como testar ?
Antes de acessar a "Gateway API", aguarde a aplicação subir e, registrar todos os serviços no eureka-server.

> **Eureka Server** **`http://localhost:8761`**

> **Gateway API** **`http://localhost:8080/alunos/v1/1`**

> **Zipkin** **`http://localhost:9411/zipkin`**