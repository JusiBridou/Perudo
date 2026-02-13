FROM node:18-alpine AS frontend-build

WORKDIR /app/frontend

COPY frontend/package*.json ./

RUN npm install

COPY frontend ./

RUN npm run build

FROM maven:3.9-eclipse-temurin-17 AS backend-build

WORKDIR /app/backend

COPY backend/pom.xml ./
COPY backend/src ./src

# Copy frontend dist into backend resources
COPY --from=frontend-build /app/frontend/dist ./src/main/resources/public

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=backend-build /app/backend/target/perudo-backend-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
