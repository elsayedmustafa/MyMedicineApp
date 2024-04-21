plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    alias(libs.plugins.kotlinKapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {


    implementation(libs.kotlin.coroutines)
    implementation(libs.javax.annotation)
    implementation(libs.javax.inject)

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.inLine)
    testImplementation(libs.kotlin.coroutines.test)
    }