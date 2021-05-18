import java.io.FileInputStream
import java.util.Properties

val secrets = Properties()
secrets.load(FileInputStream(rootProject.file("secret.properties")))

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId("com.prempal.pangolin")
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode(1)
        versionName("1.0")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")

        buildConfigField("String", "API_KEY", secrets.getProperty("apikey"))
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(deps.kotlin.stdlib)
    implementation(deps.androidx.coreKtx)
    implementation(deps.androidx.appCompat)
    implementation(deps.androidx.design)
    implementation(deps.contour)
    implementation(deps.androidx.navigationFragment)
    implementation(deps.androidx.navigationUi)

    implementation(deps.hilt.android)
    kapt(deps.hilt.compiler)

    implementation(deps.network.retrofit)
    implementation(deps.network.moshiConverter)

    testImplementation(deps.test.jUnit)
    androidTestImplementation(deps.test.android.jUnitExt)
    androidTestImplementation(deps.test.android.espressoCore)
}
