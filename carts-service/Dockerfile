FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/carts-service-0.0.1.jar
COPY ${JAR_FILE} app_carts-service.jar
ENTRYPOINT ["java", "-jar", "app_carts-service.jar"]