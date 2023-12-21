/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("com.kakao.bank.blog.search.kotlin-application-conventions")

    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("plugin.spring") version "1.9.20"
}
dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.16.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
    implementation("com.bucket4j:bucket4j-redis:8.7.0")
    implementation("com.bucket4j:bucket4j-core:8.7.0")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-amqp")

    implementation(project(":blog-search-domain"))
    implementation(project(":blog-search-utils"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
