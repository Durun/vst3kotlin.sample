import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.4.31"
}

group = "io.github.durun.vstkotlin.sample"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenLocal()
}

dependencies {
    commonMainImplementation(kotlin("stdlib-common"))
}

kotlin {
    // OS setting
    val os = org.gradle.internal.os.OperatingSystem.current()
    when {
        os.isWindows -> mingwX64("windowsX64")
        os.isMacOsX -> macosX64()
        os.isLinux -> linuxX64()
    }

    // settings for targets
    targets.withType<KotlinNativeTarget>().all {
        sourceSets {
            getByName("${targetName}Main").apply {
                kotlin.srcDir("src/nativeMain/kotlin")
                dependencies {
                    implementation("io.github.durun.vst3kotlin:hosting:0.1")
                }
            }
            getByName("${targetName}Test").apply {
                kotlin.srcDir("src/nativeTest/kotlin")
            }
        }
        binaries.executable()
    }
}