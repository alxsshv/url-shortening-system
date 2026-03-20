# original-links-getting-service

# Сборка проекта Gradle'ом
FROM gradle:9.2.1-jdk21-ubi AS builder
WORKDIR /home/gradle/project
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src ./src
RUN gradle --no-daemon clean build -x test

# Разбиваем архив по слоям используя layertools
FROM bellsoft/liberica-openjre-alpine-musl:21-cds AS layers
WORKDIR /application
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar
RUN java -Djarmode=tools -jar app.jar extract --layers --destination extracted

# Собираем образ
FROM eclipse-temurin:21-jre-ubi10-minimal AS runtime
VOLUME /tmp
WORKDIR /application
RUN groupadd --gid 1000 spring-app && useradd --uid 1000 --gid spring-app --shell /bin/bash --create-home spring-app
USER spring-app:spring-app

COPY --from=layers /application/extracted/dependencies/ ./
COPY --from=layers /application/extracted/spring-boot-loader/ ./
COPY --from=layers /application/extracted/snapshot-dependencies/ ./
COPY --from=layers /application/extracted/application/ ./

RUN java -XX:ArchiveClassesAtExit=app.jsa -Dspring.context.exit=onRefresh -jar app.jar & exit 0

EXPOSE 8086
ENTRYPOINT ["java", \
  "-XX:SharedArchiveFile=app.jsa", \
  "-Xlog:class+load:file=/tmp/classload.log", \
  "-XX:+HeapDumpOnOutOfMemoryError", \
  "-XX:HeapDumpPath=/tmp", \
  "-jar", \
  "app.jar"]