FROM node:18-alpine AS frontend-build

WORKDIR /app

COPY perudo-app/frontend . 

RUN npm install && npm run build

FROM maven:3.9-eclipse-temurin-17 AS backend-build

WORKDIR /app

COPY perudo-app/backend/pom.xml .
COPY perudo-app/backend/src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=backend-build /app/target/perudo-backend-1.0.0.jar app.jar
COPY --from=frontend-build /app/dist ./public

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
