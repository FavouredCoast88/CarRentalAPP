# Step 1: Build the application using Maven and Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
 
WORKDIR /app
 
COPY . .
 
RUN cd carrental && mvn clean package -DskipTests
 
# Step 2: Create the runtime environment with a slim Java 21 JRE
FROM eclipse-temurin:21-jre-alpine
 
WORKDIR /app
 
COPY --from=build /app/carrental/target/*.jar app.jar
 
EXPOSE 8080
 
ENTRYPOINT ["java", "-jar", "app.jar"]
