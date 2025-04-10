FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/products-service-0.0.1.jar
COPY ${JAR_FILE} app_products-service.jar
ENTRYPOINT ["java", "-jar", "app_products-service.jar"]