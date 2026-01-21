import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    `kotlin-dsl`
}

group = "com.bidone.convention"


java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
    targetCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}


dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradle.plugin)
    gradle.serviceOf<DependenciesAccessors>().classes.asFiles.forEach {
        compileOnly(files(it.absolutePath))
    }
}



gradlePlugin {
    plugins {
        create("androidProject") {
            id = libs.plugins.bidone.convention.android.application.get().pluginId
            implementationClass = "com.bidone.convention.AndroidApplicationConventionPlugin"
        }

        create("androidFeature") {
            id = libs.plugins.bidone.convention.android.feature.get().pluginId
            implementationClass = "com.bidone.convention.AndroidFeatureConventionPlugin"
        }

        create("androidDomain") {
            id = libs.plugins.bidone.convention.android.domain.get().pluginId
            implementationClass = "com.bidone.convention.AndroidDomainConventionPlugin"
        }

        create("androidCommon") {
            id = libs.plugins.bidone.convention.android.common.get().pluginId
            implementationClass = "com.bidone.convention.AndroidCommonConventionPlugin"
        }
        create("androidData") {
            id = libs.plugins.bidone.convention.android.data.get().pluginId
            implementationClass = "com.bidone.convention.AndroidDataConventionPlugin"
        }
        create("androidHilt") {
            id = libs.plugins.bidone.convention.android.hilt.get().pluginId
            implementationClass = "com.bidone.convention.HiltConventionPlugin"
        }
    }
}