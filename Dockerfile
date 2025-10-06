FROM maven:latest AS build

WORKDIR /app

COPY pom.xml .

COPY src src

RUN mvn clean package

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
