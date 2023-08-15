package com.pengxr.composepractice.config

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Configure base Android options for Application module or Library module.
 *
 * android {
 *      compileSdkVersion xx
 *      defaultConfig {
 *              minSdkVersion xxx
 *              targetSdkVersion xxx
 *      }
 * }
 */
internal fun Project.configureAndroid() {
    android {
        compileSdk = 34
        defaultConfig.minSdk = 21
        if (pluginManager.hasPlugin("com.android.application")) {
            extensions.configure<ApplicationExtension> {
                defaultConfig.targetSdk = 34
            }
        } else if (pluginManager.hasPlugin("com.android.library")) {
            extensions.configure<LibraryExtension> {
                defaultConfig.targetSdk = 34
            }
        }
    }
}

/**
 * Disable unnecessary Android instrumented tests for the [project] if there is no `androidTest` folder.
 * Otherwise, these projects would be compiled, packaged, installed and ran only to end-up with the following message:
 *
 * > Starting 0 tests on AVD
 *
 * Note: this could be improved by checking other potential sourceSets based on buildTypes and flavors.
 */
internal fun Project.disableUnnecessaryAndroidTests() {
    androidComponents {
        beforeVariants {
            it.enableAndroidTest = it.enableAndroidTest
                    && project.projectDir.resolve("src/androidTest").exists()
        }
    }
}