val kotlin_version: String by project
val logback_version: String by project
val restassured_version: String by project
val junit_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
}

group = "beeone.at"
application {
    mainClass.set("beeone.at.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:$junit_version")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:$junit_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation ("io.rest-assured:kotlin-extensions:$restassured_version")
    implementation ("org.apache.logging.log4j:log4j-core:2.19.0")
    implementation ("org.apache.logging.log4j:log4j-slf4j-impl:2.19.0")
    implementation("org.apache.commons:commons-csv:1.9.0")
    implementation("commons-io:commons-io:2.11.0")
    implementation ("org.json:json:20220924")
    implementation("com.google.code.gson:gson:2.10")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.create<Test>("investments"){
    filter {
        useJUnitPlatform(){
            includeTags("investments")
        }
    }
}