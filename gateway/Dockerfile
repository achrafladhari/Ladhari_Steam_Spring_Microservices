# BUILD STAGE
FROM maven:3.9-eclipse-temurin-17-alpine AS builder

WORKDIR /app

# Copy the Maven pom file and source code
COPY pom.xml .
COPY src ./src
# Run the Maven build
RUN mvn clean package -DskipTests
# RUN STAGE
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar gateway.jar
EXPOSE 8222

ENTRYPOINT ["java", "-jar", "gateway.jar"]
