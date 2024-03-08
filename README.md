# O Auto da Compadecida - API REST

Este projeto é uma API REST desenvolvida em Spring Boot para gerenciar os personagens da peça "O Auto da Compadecida" de Ariano Suassuna. A API permite realizar operações CRUD (Create, Read, Update, Delete) nos personagens, bem como buscar personagens por nome.

## Funcionalidades

- Listar todos os personagens
- Buscar personagem por ID
- Buscar personagem por nome
- Atualizar personagem por ID (completo e parcial)
- Atualizar personagem por nome (completo e parcial)
- Deletar personagem por ID
- Criar novo personagem

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- JUnit
- Mockito
- Lombok
- Maven

## Estrutura do projeto

O projeto segue a arquitetura em camadas, com as seguintes camadas:

- Controller: responsável por receber as requisições HTTP e retornar as respostas.
- Service: responsável pela lógica de negócio e comunicação com a camada de repositório.
- Repository: responsável pela comunicação com o banco de dados.
- Model: contém as entidades e DTOs utilizados no projeto.

## Testes

O projeto possui testes unitários para as camadas de Controller e Service, utilizando JUnit e Mockito para simular as dependências e verificar o comportamento esperado.

## Como executar

1. Clone o repositório
2. Configure as informações do banco de dados no arquivo `application.properties`
3. Execute o comando `mvn spring-boot:run` para iniciar a aplicação
4. Acesse os endpoints através de um cliente HTTP (ex: Postman) ou navegador

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests para melhorias e correções de bugs.
