# Outsera Backend Challenge

Desenvolver uma API REST em Spring Boot 3 para possibilitar a leitura da lista de indicados e vencedores da
categoria Pior Filme do Golden Raspberry Awards.

- O arquivo [movies.csv](./src/main/resources/data/movies.csv) contém a lista de filmes e demais informações.
- O arquivo [schema.sql](./src/main/resources/schema.sql) contém a definição da tabela que armazena os registros.

## Requisitos da API

Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois
prêmios mais rápido, seguindo a especificação de formato definida.

## Estrutura de Arquivos

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
    │   │                   └── producer
    │   │                       └── ProducerDTO.java
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

## Rodando a API

O processo roda na porta 8080. O endereço padrão é http://localhost:8080.

Pré-requisitos:

- Java Development Kit (JDK) 17 ou superior instalado em sua máquina:

```bash
$ java -version
openjdk version "17.0.16" 2025-07-15
OpenJDK Runtime Environment (build 17.0.16+8-Ubuntu-0ubuntu122.04.1)
OpenJDK 64-Bit Server VM (build 17.0.16+8-Ubuntu-0ubuntu122.04.1, mixed mode, sharing)
```

Torne o arquivo ``mvnw``, no diretório raíz do projeto, executável:

```bash
$ chmod +x mvnw
```

Execute os seguinte comando no diretório raiz do projeto para baixar as dependências:

```bash
$ ./mvnw install
```

A saída será algo como:

```bash

[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------------< com.outsera:api >---------------------------
[INFO] Building api 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 

...

[INFO] --- install:3.1.4:install (default-install) @ api ---
[INFO] Installing /home/juliano/Public/outsera-challenge-backend/pom.xml to /home/juliano/.m2/repository/com/outsera/api/0.0.1-SNAPSHOT/api-0.0.1-SNAPSHOT.pom
[INFO] Installing /home/juliano/Public/outsera-challenge-backend/target/api-0.0.1-SNAPSHOT.jar to /home/juliano/.m2/repository/com/outsera/api/0.0.1-SNAPSHOT/api-0.0.1-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  9.055 s
[INFO] Finished at: 2025-10-02T11:26:53-03:00
[INFO] ------------------------------------------------------------------------

```

Execute os seguinte comando no diretório raiz do projeto para subir a API:

```bash
$ ./mvnw spring-boot:run
```

A saída será algo como:

```bash
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

## Testando a API

Execute o seguinte comando na raíz do projeto:

```bash
$ ./mvnw test
````

A saída será algo como:

```bash
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------------< com.outsera:api >---------------------------
[INFO] Building api 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ api ---
[INFO] Copying 1 resource from src/main/resources to target/classes
[INFO] Copying 2 resources from src/main/resources to target/classes
[INFO] 
[INFO] --- compiler:3.14.0:compile (default-compile) @ api ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ api ---
[INFO] skip non existing resourceDirectory /home/juliano/Public/outsera-challenge-backend/src/test/resources
[INFO] 
[INFO] --- compiler:3.14.0:testCompile (default-testCompile) @ api ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- surefire:3.5.4:test (default-test) @ api ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.outsera.api.ApplicationTests
18:59:56.701 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [com.outsera.api.ApplicationTests]: ApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
18:59:56.797 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration com.outsera.api.Application for test class com.outsera.api.ApplicationTests

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.6)
 
...

[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.555 s -- in com.outsera.api.ApplicationTests
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.191 s
[INFO] Finished at: 2025-10-01T18:55:20-03:00
[INFO] ------------------------------------------------------------------------

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