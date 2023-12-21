import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.kakao.bank.blog.search.kotlin-application-conventions")

    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("plugin.spring") version "1.9.20"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.apache.commons:commons-text")
    implementation(project(":blog-search-utils"))
    implementation(project(":blog-search-domain"))
    implementation(project(":blog-search-infra"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
