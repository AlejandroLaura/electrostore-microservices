FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/config-server-0.0.1.jar
COPY ${JAR_FILE} app_config-server.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app_config-server.jar"]