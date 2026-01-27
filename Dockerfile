FROM maven:3.9-eclipse-temurin-17 AS backend-build

WORKDIR /app

COPY perudo-app/backend/pom.xml .
COPY perudo-app/backend/src ./src

RUN mvn clean package -DskipTests

FROM node:18-alpine AS frontend-build

WORKDIR /app

COPY perudo-app/frontend . 

RUN npm install && npm run build

FROM nginx:alpine

COPY --from=backend-build /app/target/perudo-backend-1.0.0.jar /app/app.jar
COPY --from=frontend-build /app/dist /usr/share/nginx/html

COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
