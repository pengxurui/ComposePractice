package com.pengxr.composepractice.feature

import configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project

class JetpackComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureAndroidCompose()
        }
    }
}