/**
 * Common module configurations for JVM (non-Android)
 */
package com.pengxr.composepractice.module

import configureKotlinForJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureKotlinForJvm()
        }
    }
}