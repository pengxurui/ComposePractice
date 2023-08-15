package com.pengxr.composepractice.module

import com.pengxr.composepractice.config.configureAndroid
import com.pengxr.composepractice.config.disableUnnecessaryAndroidTests
import configureKotlinForAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

/**
 * Common module configurations for Android Library
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }
            configureAndroid()
            configureKotlinForAndroid()
            disableUnnecessaryAndroidTests()

            dependencies {
                add("androidTestImplementation", kotlin("test"))
                add("testImplementation", kotlin("test"))
            }
        }
    }
}