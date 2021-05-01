### Tech Stack
- Java 8+
- Spring Boot
- Maven
- Docker

### O que é ?
Um simples caso de uso, que representa uma escola qualquer. 
Para isso foi utilizado conceito de micro-serviços, onde pequenos serviços registram-se em um eureka-server.
Os serviços criados, não se comunicam entre si. Para isso foi utilizado um serviço que serve como um gateway, é esse serviço que é responsável por se comunicar com todos os outros, utilizando [@FeignClient](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html).
Todos os serviços foram configurados com Dockerfile. Além disso, na raiz no projeto foi criado um arquivo docker-compose.yml, para facilitar buld e deploy da aplicação.

### Arquitetura
![](https://github.com/lucianoortizsilva/microservices-case-escola/blob/main/static/github/arquitetura.png?raw=true)

### Como rodar ?
- Realize o build em todos os projeto, executando **`mvn clean package`**
- Na raiz do projeto, execute **`docker-compose up`**, para criar as imagens e realizar o deploy

### Como testar ?
Aguarde a aplicação subir, e registrar todos os serviços no eureka-server.
Para visulizar se está tudo ok, pode acessar: **`http://localhost:8761`**
Para testar os serviços, realize uma chamada ao gateway da aplicação.
> **GET** **`http://localhost:8080/boletins/aluno/1`**
