val versions = mapOf(
    "awaitility_version" to "4.3.0",
    "mapstruct_version" to "1.6.3",
    "hashids_version" to "1.0.3",
    "flyway_version" to "11.7.2",
    "testcontainers_version" to "2.0.3"
)

plugins {
	java
	id("org.springframework.boot") version "4.0.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.github.alxsshv"
version = "1.0.0-SNAPSHOT"
description = "Creating short link service"

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
        mavenBom("org.testcontainers:testcontainers-bom:2.0.3")
        mavenBom("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom:2.23.0")
    }
}

dependencies {
    // SPRING
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-flyway")

    // PERSISTENCE
    implementation("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-database-postgresql:${versions["flyway_version"]}")

    // OBSERVABILITY
    implementation("io.opentelemetry:opentelemetry-exporter-otlp")
    implementation("io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("io.micrometer:micrometer-observation")
    implementation("io.micrometer:micrometer-tracing")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    // HELPERS
    implementation("org.hashids:hashids:${versions["hashids_version"]}")
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")

    // TEST
	testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:testcontainers-junit-jupiter:${versions["testcontainers_version"]}")
    testImplementation("org.testcontainers:testcontainers-postgresql:${versions["testcontainers_version"]}")
    testImplementation("org.awaitility:awaitility:${versions["awaitility_version"]}")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
