import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.pengxr.composepractice.buildlogic"

// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidFeature") {
            id = "convention.feature"
            implementationClass = "com.pengxr.composepractice.module.AndroidFeatureConventionPlugin"
        }
        register("androidApplication") {
            id = "convention.application"
            implementationClass = "com.pengxr.composepractice.module.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "convention.library"
            implementationClass = "com.pengxr.composepractice.module.AndroidLibraryConventionPlugin"
        }
        register("jvmLibrary") {
            id = "convention.jvm"
            implementationClass = "com.pengxr.composepractice.module.JvmLibraryConventionPlugin"
        }
        register("jetpackCompose") {
            id = "convention.compose"
            implementationClass = "com.pengxr.composepractice.feature.JetpackComposeConventionPlugin"
        }
    }
}