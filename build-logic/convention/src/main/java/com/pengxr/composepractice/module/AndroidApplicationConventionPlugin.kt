package com.pengxr.composepractice.module

import com.pengxr.composepractice.config.configureAndroid
import configureKotlinForAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Common module configurations for Android Application.
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            configureAndroid()
            configureKotlinForAndroid()
        }
    }
}