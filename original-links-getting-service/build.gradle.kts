val versions = mapOf(
    "mapstruct_version" to "1.6.3",
    "postgresql_version" to "42.7.3",
    "liquibase_version" to "4.29.2"
)

plugins {
    java
    id("org.springframework.boot") version "3.5.8"
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

dependencies {
    // SPRING
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // PERSISTENCE
    implementation("org.postgresql:postgresql:${versions["postgresql_version"]}")

    // HELPERS
    implementation("org.mapstruct:mapstruct:${versions["mapstruct_version"]}")
    annotationProcessor("org.mapstruct:mapstruct-processor:${versions["mapstruct_version"]}")
    compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

    // TEST
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


tasks.withType<Test> {
	useJUnitPlatform()
}
