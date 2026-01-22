plugins {
    alias(libs.plugins.bidone.convention.android.data)
}

android {
    namespace = "com.bidone.data"

    buildFeatures {
        buildConfig = true
    }


    defaultConfig {
        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://my-json-server.typicode.com\""
        )
    }
}

dependencies {

}