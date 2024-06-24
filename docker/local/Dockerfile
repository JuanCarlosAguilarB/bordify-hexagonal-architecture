FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia el archivo de JAR construido al contenedor (en el paso de construcción, este archivo se regenerará)
COPY target/*.jar bordify-monolith-0.0.1-SNAPSHOT.jar.jar

# Expone el puerto 8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "bordify-monolith-0.0.1-SNAPSHOT.jar.jar"]
