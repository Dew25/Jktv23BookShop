FROM openjdk:21-ea-1-jdk-slim
COPY ./target/*.jar /app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar", "--server.address=0.0.0.0"]