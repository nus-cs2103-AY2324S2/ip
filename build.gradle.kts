import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    application
    // Apply the Kotlin JVM plugin if you're using Kotlin
    kotlin("jvm") version "2.0.0-Beta3"
}

group = "com.yourname.duke"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JUnit test dependencies
    testImplementation("junit:junit:4.13.2")

    // If you're using Kotlin, you might have Kotlin standard library dependencies
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // ... other dependencies ...
}

application {
    mainClass.set("Duke")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.test {
    useJUnitPlatform()
}



