# Etapa 1: Build (com cache de dependências)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia apenas o pom.xml primeiro para baixar dependências (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código fonte e faz o build
COPY src ./src
RUN mvn clean package -DskipTests -B

# Etapa 2: Run (Imagem final leve)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Porta padrão do Render
EXPOSE 8888

ENTRYPOINT ["java","-jar","app.jar"]