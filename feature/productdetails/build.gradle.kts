plugins {
    alias(libs.plugins.bidone.convention.android.feature)
}

android {
    namespace = "com.bidone.productdetails"

}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
}