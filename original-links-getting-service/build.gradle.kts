import org.gradle.kotlin.dsl.implementation

val versions = mapOf(
    "postgresql_version" to "42.7.3",
    "liquibase_version" to "4.29.2",
    "jedis_version" to "7.2.0",
    "testcontainers_postgres_version" to "1.21.4",
    "testcontainers_redis_version" to "2.2.4",
    "logback_classic_version" to "1.5.18",
    "micrometer_version" to "1.16.1",
    "micrometer_tracing_version" to "1.6.1",
    "opentelemetry_version" to "1.58.0",
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
    implementation("io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter")
    implementation("io.opentelemetry:opentelemetry-exporter-otlp:${versions["opentelemetry_version"]}")
    implementation("io.opentelemetry:opentelemetry-sdk:${versions["opentelemetry_version"]}")
    implementation("io.micrometer:micrometer-registry-prometheus:${versions["micrometer_version"]}")
    implementation("io.micrometer:micrometer-observation:${versions["micrometer_version"]}")
    implementation("io.micrometer:micrometer-tracing:${versions["micrometer_tracing_version"]}")
    implementation("io.micrometer:micrometer-tracing-bridge-otel:${versions["micrometer_tracing_version"]}")
    implementation("ch.qos.logback:logback-classic:${versions["logback_classic_version"]}")

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