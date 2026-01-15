import org.gradle.kotlin.dsl.implementation

val versions = mapOf(
    "postgresql_version" to "42.7.3",
    "liquibase_version" to "4.29.2",
    "jedis_version" to "7.2.0",
    "testcontainers_postgres_version" to "1.21.4",
    "testcontainers_redis_version" to "2.2.4"
)

val mockitoAgent = configurations.create("mockitoAgent")

plugins {
    java
    id("org.springframework.boot") version "4.0.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.github.alxsshv"
version = "1.0.0-SNAPSHOT"
description = "a service for getting original links using a short link"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:1.21.4")
        mavenBom("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom:2.23.0")
    }
}

dependencies {
    // SPRING
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // PERSISTENCE
    implementation("org.postgresql:postgresql:${versions["postgresql_version"]}")

    // CACHE
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("redis.clients:jedis:${versions["jedis_version"]}")

    // OBSERVABILITY
    implementation("io.opentelemetry:opentelemetry-exporter-otlp")
    implementation("io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("io.micrometer:micrometer-observation")
    implementation("io.micrometer:micrometer-tracing")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    // HELPERS
    compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

    // TEST

	testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:postgresql:${versions["testcontainers_postgres_version"]}")
    testImplementation("com.redis:testcontainers-redis:${versions["testcontainers_redis_version"]}")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}



tasks {
    test {
        jvmArgs.add("-javaagent:${mockitoAgent.asPath}")
    }
}