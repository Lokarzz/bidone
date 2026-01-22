plugins {
    alias(libs.plugins.bidone.convention.android.domain)

}

android {
    namespace = "com.bidone.domain"


}

dependencies {
    implementation(projects.core.data)
}