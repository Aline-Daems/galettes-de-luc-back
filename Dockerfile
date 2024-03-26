
FROM maven:3.6.3-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

# Etape 2 créer l'image
FROM openjdk:17
ARG JAR_FILE=/app/target/*.jar
COPY --from=builder ${JAR_FILE} Galettes-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/Galettes-0.0.1.jar"]