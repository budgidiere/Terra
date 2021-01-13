import com.dfsek.terra.configureCommon
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
}

configureCommon()

group = "com.dfsek.terra"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io/") }
}

dependencies {
    "shadedApi"(project(":common"))
    "shadedImplementation"("com.github.Querz:NBT:5.2") // Standalone NBT API
    "shadedImplementation"("org.yaml:snakeyaml:1.27")
    "shadedImplementation"("com.googlecode.json-simple:json-simple:1.1.1")
    "shadedImplementation"("it.unimi.dsi:fastutil:8.4.4")
}

tasks.named<ShadowJar>("shadowJar") {
    relocate("net.querz", "com.dfsek.terra.libs.nbt")
}