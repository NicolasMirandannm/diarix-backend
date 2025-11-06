# ===== STAGE 1: BUILD =====
FROM gradle:8.10.2-jdk23-alpine AS build

WORKDIR /home/gradle/project

# Copia todo o projeto para dentro da imagem
COPY . .

# Usa o Gradle da imagem (não usa gradlew)
RUN gradle clean bootJar -x test --no-daemon

# ===== STAGE 2: RUNTIME =====
FROM eclipse-temurin:23-jre-alpine

WORKDIR /app

# Copia o jar gerado do estágio de build
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
