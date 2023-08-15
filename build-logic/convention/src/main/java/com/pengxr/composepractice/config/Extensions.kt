package com.pengxr.composepractice.config

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * <p>
 * Created by Peng Xurui on 15/8/2023
 */

internal val Project.libsCatalog
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

//  ApplicationExtension -> CommonExtension -> BaseExtension
//  LibraryExtension -> CommonExtension -> BaseExtension
private val Project.androidExtension
    get(): CommonExtension<*, *, *, *, *> = extensions.getByName("android") as CommonExtension<*, *, *, *, *>

private val Project.androidComponentsExtension
    get(): LibraryAndroidComponentsExtension = extensions.getByType()

internal fun Project.android(config: Action<CommonExtension<*, *, *, *, *>>) {
    config.execute(androidExtension)
}

internal fun Project.androidComponents(config: Action<LibraryAndroidComponentsExtension>) {
    config.execute(androidComponentsExtension)
}