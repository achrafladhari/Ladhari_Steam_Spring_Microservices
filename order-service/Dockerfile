#BUILD
FROM maven:3.9-eclipse-temurin-17-alpine AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests
#RUN
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app



COPY --from=builder /app/target/*.jar order-service.jar

EXPOSE 8060

ENTRYPOINT [ "java","-jar","order-service.jar" ]
