plugins {
    alias(libs.plugins.bidone.convention.android.application)

}

android {
    namespace = "com.bidone.bidtest"


    defaultConfig {
        applicationId = "com.bidone.bidtest"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


}

dependencies {
    implementation(projects.feature.products)
}