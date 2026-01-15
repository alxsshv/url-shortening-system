FROM gradle:9.2.1-jdk21-ubi AS build

WORKDIR /home/gradle/project

COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src ./src

RUN gradle --no-daemon clean build -x test

FROM eclipse-temurin:21-jre-ubi10-minimal AS runtime

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar /app.jar

EXPOSE 8086
CMD ["java", "-jar", "/app.jar"]
