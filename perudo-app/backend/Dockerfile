FROM openjdk:25-slim

WORKDIR /app

COPY target/perudo-backend-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
