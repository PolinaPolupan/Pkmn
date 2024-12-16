plugins {
    java
    application
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.autonomousapps.dependency-analysis") version "2.4.2"
}

group = "ru.mirea"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    // Spring Boot (Aligned with Spring Boot 2.2.4.RELEASE)
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.2.4.RELEASE"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("org.springframework.boot:spring-boot-configuration-processor")

    // Database
    implementation("org.postgresql:postgresql")
    runtimeOnly("org.flywaydb:flyway-core")

    // JSON Handling
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")

    // HTTP Client and Utilities
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.guava:guava:20.0")
    implementation("com.google.code.gson:gson:2.7")

    // Hibernate
    implementation("org.hibernate.orm:hibernate-core:6.3.0.Final")
    implementation("com.vladmihalcea:hibernate-types-60:2.21.1") // Compatible with Hibernate 5.x

    // Jakarta Servlet API
    implementation("javax.servlet:javax.servlet-api:4.0.1") // Compatible with Spring Boot 2.x

    // Annotations
    compileOnly("org.jetbrains:annotations:24.1.0")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.5")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.5")
}


tasks {
    test {
        useJUnitPlatform()
    }
}

tasks {
    bootJar {
        layered {
            enabled=true
        }
        archiveBaseName.set("app")
        archiveVersion.set("") // Removes version from the file name
    }
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    layered {
        enabled=true
    }
    enabled = true
}