FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/eureka-server-0.0.1.jar
COPY ${JAR_FILE} app_eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app_eureka-server.jar"]