### Tech Stack
- Java 8+
- Spring Boot
- Maven
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
No exemplo implementado, um client deseja acessar o boletim de um teterminado aluno, onde:\
 - Todos os serviços se registram no eureka server;
 - O Client acessa a escola-gateway > **GET** **`http://localhost:8080/alunos/v1/1/boletim`**, que por sua vez acessa o ms-aluno;
 - Para montar o boletim, o ms-aluno acessa as suas 2 dependências: ms-professor e ms-avaliacao;  
Todos os serviços foram configurados com Dockerfile. 
Além disso, na raiz no projeto foi criado um arquivo docker-compose.yml, para facilitar build e deploy da aplicação.

### Arquitetura
![](https://github.com/lucianoortizsilva/microservices-case-escola/blob/main/static/github/arquitetura.png?raw=true)

### Como rodar ?
- Realize o build em todos os projeto, executando **`mvn clean package`**
- Na raiz do projeto, execute **`docker-compose up`**, para criar as imagens e realizar o deploy

### Como testar ?
Aguarde a aplicação subir, e registrar todos os serviços no eureka-server.\
Para visualizar se está tudo ok, pode acessar: **`http://localhost:8761`**\
Para testar os serviços, realize uma chamada ao gateway da aplicação.\

> **GET** **`http://localhost:8080/alunos/v1/1`**

### Bônus
- Implementação de circuitbreaker ao chamar as dependências do ms-aluno, utilizando a biblioteca do `resilience4j`
- Implementação de cache, utilizando @Cacheable
