# Outsera Backend Challenge

Desenvolver uma API RESTful em Spring Boot 3 para possibilitar a leitura da lista de indicados e vencedores da
categoria Pior Filme do Golden Raspberry Awards.

## Requisitos da API

Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois
prêmios mais rápido, seguindo a especificação de formato definida.

## Estrutura de Arquivos do Projeto

```
.
├── LICENSE
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── outsera
    │   │           └── api
    │   │               ├── Application.java
    │   │               └── movies
    │   │                   ├── MovieController.java
    │   │                   ├── MovieDataLoader.java
    │   │                   ├── Movie.java
    │   │                   ├── MovieRepository.java
    │   │                   ├── MovieService.java
    │   │                   └── util
    │   │                       └── ProducerInterval.java
    │   └── resources
    │       ├── application.properties
    │       ├── data
    │       │   └── movies.csv
    │       ├── schema.sql
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── outsera
                    └── api
                        └── ApplicationTests.java

```
