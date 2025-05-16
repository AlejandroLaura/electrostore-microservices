# Electrostore Microservices 🛍️

Sistema de e-commerce desarrollado con arquitectura de microservicios en Java, Spring Boot y Spring Cloud. Los microservicios gestionan productos, carritos y ventas de forma distribuida, simulando una tienda online moderna con servicios CRUD completos y comunicación entre sí mediante Feign Client. Integración con Eureka Server para descubrimiento de servicios, ruteo dinámico con API Gateway y centralización de configuración vía Config Server. Desplegado con Docker y Docker Compose.

## 🧩 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Cloud (Eureka, Config Server, Gateway)
- Spring Data JPA
- MySQL 8
- Docker y Docker Compose
- Maven

## 🔧 Microservicios incluidos

| Microservicio     | Puerto | Descripción                                     |
|-------------------|--------|-------------------------------------------------|
| eureka-server     | 8761   | Registro de servicios                           |
| config-server     | 8081   | Centralización de configuración                 |
| api-gateway       | 8080   | Punto de entrada a los microservicios          |
| products-service  | 8083   | Gestión de productos                            |
| carts-service     | 8084   | Gestión de carritos de compra                   |
| sales-service     | 8085   | Gestión de ventas                               |

📝 Notas
El proyecto utiliza una base de datos MySQL por servicio.

Las variables de entorno están configuradas en docker-compose.yml.

El repositorio de configuración está conectado al Config Server mediante GitHub.

🎯 Objetivo
Este proyecto forma parte de mi portfolio como desarrollador backend. Está enfocado en demostrar mis conocimientos en arquitectura de microservicios, integración continua, y despliegue con contenedores.
