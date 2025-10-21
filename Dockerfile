FROM eclipse-temurin:21-jdk-jammy as builder

WORKDIR /app

COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

RUN chmod +x ./gradlew

RUN ./gradlew build --no-daemon -x test


FROM eclipse-temurin:21-jre-jammy

WORKDIR /app


COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
