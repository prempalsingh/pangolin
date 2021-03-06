object deps {

    const val agp = "com.android.tools.build:gradle:4.2.1"

    object kotlin {
        private const val version = "1.5.0"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object androidx {
        private const val navVersion = "2.3.5"
        const val coreKtx = "androidx.core:core-ktx:1.3.2"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val design = "com.google.android.material:material:1.3.0"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navVersion"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navVersion"
        const val navigationSafeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion"
        const val paging = "androidx.paging:paging-runtime:3.0.0"
    }

    object hilt {
        private const val version = "2.35"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
    }

    object network {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object test {
        object android {
            const val jUnitExt = "androidx.test.ext:junit:1.1.2"
            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        }

        const val jUnit = "junit:junit:4.13"
        const val mockk = "io.mockk:mockk:1.11.0"
        const val truth = "com.google.truth:truth:1.1.2"
        const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
    }

    object chucker {
        private const val version = "3.4.0"
        const val lib = "com.github.chuckerteam.chucker:library:$version"
        const val libNoOp = "com.github.chuckerteam.chucker:library-no-op:$version"
    }

    const val contour = "app.cash.contour:contour:1.1.0"
}
