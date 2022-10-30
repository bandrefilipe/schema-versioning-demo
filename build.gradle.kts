import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

dependencies {
    // Project Modules
    implementation(project(":application"))
    implementation(project(":domain"))
    implementation(project(":infrastructure"))

    // Spring Starter
    implementation("org.springframework.boot:spring-boot-starter")
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
allprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    version = "0.0.1-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_17

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        // Lightweight logging framework for Kotlin
        implementation("io.github.microutils:kotlin-logging:3.0.2")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.majorVersion
            freeCompilerArgs = listOf(
                "-Xjsr305=strict",
                "-Xemit-jvm-type-annotations",
            )
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
subprojects {
    apply {
        plugin("java-library")
    }

    dependencyManagement {
        imports {
            mavenBom(SpringBootPlugin.BOM_COORDINATES)
        }
    }

    tasks.withType<Jar> {
        archiveBaseName.set(rootProject.name)
        archiveAppendix.set(project.name)
    }
}
