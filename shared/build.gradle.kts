import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeSimulatorTest
import java.io.ByteArrayOutputStream

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.serialization)
            implementation(libs.kotlin.datetime)
            implementation(libs.ktor.core)
            implementation(libs.kotlin.coroutines)
            implementation(libs.ktor.contentNegotiation)
            implementation(libs.ktor.contentEncoding)
            implementation(libs.ktor.json)
            implementation(libs.ktor.serialization)
            implementation(libs.sqldelight.runtime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlin.coroutines.test)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin)
            implementation(libs.sqldelight.nativeDriver)
        }
        androidMain.dependencies {
            implementation(libs.ktor.okhttp)
            implementation(libs.sqldelight.androidDriver)
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(libs.sqldelight.jvm)
            }
        }
    }

    // Workaround for testing till resolving KT-61470 (target version Kotlin 2.1.0):
    // https://youtrack.jetbrains.com/issue/KT-61470
    // Original issue and workaround described here:
    // https://youtrack.jetbrains.com/issue/KT-38317
    val deviceName = project.findProperty("iosDevice") as? String ?: "iPhone 15"

    tasks.register<Exec>("bootIOSSimulator") {
        isIgnoreExitValue = true
        val errorBuffer = ByteArrayOutputStream()
        errorOutput = ByteArrayOutputStream()
        commandLine("xcrun", "simctl", "boot", deviceName)

        doLast {
            val result = executionResult.get()
            if (result.exitValue != 148 && result.exitValue != 149) { // ignoring device already booted errors
                println(errorBuffer.toString())
                result.assertNormalExitValue()
            }
        }
    }

    tasks.withType<KotlinNativeSimulatorTest>().configureEach {
        dependsOn("bootIOSSimulator")
        standalone.set(false)
        device.set(deviceName)
    }
    // End of workaround section KT-61470
}

android {
    namespace = "by.audiobooks.mob.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

sqldelight {
    databases {
        create("AudiobooksByDB") {
            packageName.set("by.audiobooks.mob.cache")
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += "-Xexpect-actual-classes"
    }
}
