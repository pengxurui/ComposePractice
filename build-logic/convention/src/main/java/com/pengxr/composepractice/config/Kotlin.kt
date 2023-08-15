/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.pengxr.composepractice.config.android
import com.pengxr.composepractice.config.libsCatalog
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure base Kotlin options for Android.
 *
 * apply plugin: 'kotlin-android'
 *
 * android {
 *     compileOptions {
 *         sourceCompatibility JavaVersion.VERSION_11
 *         targetCompatibility JavaVersion.VERSION_11
 *         isCoreLibraryDesugaringEnabled = true
 *     }
 *     kotlinOptions {
 *         jvmTarget = '1.11'
 *     }
 *     dependencies {
 *         coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs'
 *     }
 * }
 */
internal fun Project.configureKotlinForAndroid() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.android")
    }
    android {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
            isCoreLibraryDesugaringEnabled = true
        }
    }

    // Configure base Kotlin options
    configureKotlin()

    dependencies {
        // Java 11+ APIs available through desugaring
        // https://developer.android.com/studio/write/java11-minimal-support-table
        add("coreLibraryDesugaring", libsCatalog.findLibrary("android.desugarJdkLibs").get())
    }
}

/**
 * Configure base Kotlin options for JVM (non-Android).
 *
 * apply plugin: 'kotlin-jvm'
 *
 * compileOptions {
 *      sourceCompatibility = JavaVersion.VERSION_11
 *      targetCompatibility = JavaVersion.VERSION_11
 * }
 */
internal fun Project.configureKotlinForJvm() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.jvm")
    }

    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    configureKotlin()
}

/**
 * Configure base Kotlin options.
 *
 * kotlinOptions {
 *      jvmTarget = "11"
 * }
 */
private fun Project.configureKotlin() {
    // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            // Set JVM target to 11
            jvmTarget = JavaVersion.VERSION_11.toString()
            // Treat all Kotlin warnings as errors (disabled by default)
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                // Enable experimental coroutines APIs, including Flow
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
            )
        }
    }
}