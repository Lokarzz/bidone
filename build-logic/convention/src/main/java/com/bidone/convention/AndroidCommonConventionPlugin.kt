package com.bidone.convention


import com.android.build.gradle.LibraryExtension
import com.bidone.convention.extensions.alias
import com.bidone.convention.extensions.applyCompileOptions
import com.bidone.convention.extensions.applyDefaultConfiguration
import com.bidone.convention.extensions.applyKotlinJvmCompilerOptions
import com.bidone.convention.extensions.debugImplementation
import com.bidone.convention.extensions.implementation
import com.bidone.convention.extensions.kotlinOptions
import com.bidone.convention.extensions.libraryExtension
import com.bidone.convention.extensions.libs
import com.bidone.convention.extensions.testImplementation
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies

class AndroidCommonConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            val libs = libs()
            applyPlugins(libs)

            android {
                compileSdk = libs.versions.compileSdk.get().toInt()
                applyCompileOptions(libs)
                applyDefaultConfiguration(libs)

                kotlinOptions {
                    applyKotlinJvmCompilerOptions(libs)
                }

            }
            dependencies {
                applyDependencies(libs)
            }
        }
    }


    private fun Project.applyPlugins(libs: LibrariesForLibs) {
        alias(libs.plugins.android.library)
        alias(libs.plugins.kotlin.android)
        alias(libs.plugins.kotlin.compose)
        alias(libs.plugins.bidone.convention.android.hilt)
    }

    private fun DependencyHandlerScope.applyDependencies(libs: LibrariesForLibs) {
        implementation(libs.androidx.core.ktx)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.compose.ui)
        implementation(libs.androidx.compose.ui.graphics)
        implementation(libs.androidx.compose.ui.tooling.preview)
        implementation(libs.androidx.compose.material3)
        implementation(libs.androidx.compose.material.icons.core)
        implementation(libs.kotlinx.coroutines.android)
        implementation(platform(libs.coil.bom))
        implementation(libs.coil.compose)
        implementation(libs.coil.network.okhttp)


        debugImplementation(libs.androidx.compose.ui.tooling)
        debugImplementation(libs.androidx.compose.ui.test.manifest)
        testImplementation(libs.junit)
        testImplementation(libs.kotlinx.coroutines.test)

    }

    private fun Project.android(block: LibraryExtension.() -> Unit) {
        libraryExtension().apply(block)
    }


}