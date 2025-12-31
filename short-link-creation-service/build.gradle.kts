val versions = mapOf(
    "mapstruct_version" to "1.6.3",
    "hashids_version" to "1.0.3",
    "flyway_version" to "11.7.2",

)

plugins {
	java
	id("org.springframework.boot") version "3.5.9"
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

dependencies {
    // SPRING
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // PERSISTENCE
    implementation("org.postgresql:postgresql")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:${versions["flyway_version"]}")

    // HELPERS
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
    implementation("org.mapstruct:mapstruct:${versions["mapstruct_version"]}")
    annotationProcessor("org.mapstruct:mapstruct-processor:${versions["mapstruct_version"]}")
    implementation("org.hashids:hashids:${versions["hashids_version"]}")

    // TEST
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
