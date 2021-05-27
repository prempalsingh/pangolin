object deps {

    const val agp = "com.android.tools.build:gradle:4.2.1"

    object kotlin {
        private const val version = "1.5.0"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object androidx {
        const val coreKtx = "androidx.core:core-ktx:1.3.2"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val design = "com.google.android.material:material:1.3.0"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:2.3.5"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:2.3.5"
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
    }

    const val contour = "app.cash.contour:contour:1.1.0"
}
