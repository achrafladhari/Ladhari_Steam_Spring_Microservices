#BUILD
FROM maven:3.9-eclipse-temurin-17-alpine AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests
#RUN
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar discovery-service.jar

EXPOSE 8761
ENTRYPOINT [ "java","-jar","discovery-service.jar" ]
