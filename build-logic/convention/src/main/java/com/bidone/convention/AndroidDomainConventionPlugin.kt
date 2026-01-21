package com.bidone.convention


import com.android.build.gradle.LibraryExtension
import com.bidone.convention.extensions.alias
import com.bidone.convention.extensions.applyCompileOptions
import com.bidone.convention.extensions.applyDefaultConfiguration
import com.bidone.convention.extensions.applyKotlinJvmCompilerOptions
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

class AndroidDomainConventionPlugin : Plugin<Project> {
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
        alias(libs.plugins.bidone.convention.android.hilt)
    }

    private fun DependencyHandlerScope.applyDependencies(libs: LibrariesForLibs) {
        implementation(libs.kotlinx.coroutines.android)
        testImplementation(libs.junit)
        testImplementation(libs.kotlinx.coroutines.test)

        implementation(libs.retrofit)
        implementation(libs.converter.gson)

    }
    private fun Project.android(block: LibraryExtension.() -> Unit) {
        libraryExtension().apply(block)
    }


}