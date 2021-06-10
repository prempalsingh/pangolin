import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import java.io.FileInputStream
import java.util.Properties

val secrets = Properties()
val secretsFile = rootProject.file("secret.properties")
if (secretsFile.exists()) {
    secrets.load(FileInputStream(secretsFile))
}

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("dagger.hilt.android.plugin")
    id("com.github.ben-manes.versions") version "0.39.0"
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

        buildConfigField(
            "String", "API_KEY",
            secrets.getProperty("apikey", "\"PLACEHOLDER\"")
        )
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation(deps.androidx.paging)

    implementation(deps.hilt.android)
    kapt(deps.hilt.compiler)

    implementation(deps.network.retrofit)
    implementation(deps.network.moshiConverter)

    debugImplementation(deps.chucker.lib)
    releaseImplementation(deps.chucker.libNoOp)

    testImplementation(deps.test.jUnit)
    testImplementation(deps.test.mockk)
    testImplementation(deps.test.truth)
    testImplementation(deps.kotlin.coroutinesTest)
    testImplementation(deps.test.coreTesting)

    androidTestImplementation(deps.test.android.jUnitExt)
    androidTestImplementation(deps.test.android.espressoCore)
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}
