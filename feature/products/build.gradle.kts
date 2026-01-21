plugins {
    alias(libs.plugins.bidone.convention.android.feature)
}

android {
    namespace = "com.bidone.products"

}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
}