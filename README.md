# Outsera Backend Challenge

Desenvolver uma API REST em Spring Boot 3 para possibilitar a leitura da lista de indicados e vencedores da
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

## Rodando a api

Pré-requisitos:

- Java Development Kit (JDK) 17 ou superior instalado em sua máquina

Torne o arquivo ``mvnw`` executável:

```bash
$ chmod +x mvnw
```

Execute o seguinte comando na raíz do projeto:

```bash
$ ./mvnw spring-boot:run

[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------------< com.outsera:api >---------------------------
[INFO] Building api 0.0.1-SNAPSHOT
[INFO]   from pom.xml
...
[INFO] Attaching agents: []

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.6)

2025-10-01T12:54:18.051-03:00  INFO 3864347 --- [api] [           main] com.outsera.api.ApiApplication           : Starting ApiApplication using Java 17.0.16 with PID 3864347 (/home/juliano/Public/outsera-challenge-backend/target/classes started by juliano in /home/juliano/Public/outsera-challenge-backend)
2025-10-01T12:54:18.054-03:00  INFO 3864347 --- [api] [           main] com.outsera.api.ApiApplication           : No active profile set, falling back to 1 default profile: "default"
2025-10-01T12:54:18.469-03:00  INFO 3864347 --- [api] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2025-10-01T12:54:18.485-03:00  INFO 3864347 --- [api] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 8 ms. Found 0 JPA repository interfaces.
2025-10-01T12:54:18.800-03:00  INFO 3864347 --- [api] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
```

## Endpoint

- **`GET` /api/movies**

```bash
$ curl --location 'http://localhost:8080/api/movies'
```

<details>
<summary><b>Response</b></summary>

```json
{
  "min": [
    {
      "producer": "Bo Derek",
      "interval": 6,
      "previousWin": 1984,
      "followingWin": 1990
    }
  ],
  "max": [
    {
      "producer": "Matthew Vaughn",
      "interval": 13,
      "previousWin": 2002,
      "followingWin": 2015
    }
  ]
}
```
</details>

---

## Referências

- [**Spring Boot**](https://spring.io/projects/spring-boot)
- [**IntelliJ IDEA Community Edition**](https://www.jetbrains.com/idea/download/?section=linux)