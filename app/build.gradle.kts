plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "sh.damon.kbcrootbypass"
    compileSdk = 34

    defaultConfig {
        applicationId = "sh.damon.kbcrootbypass"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "0.1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    compileOnly(libs.xposed)
    implementation(libs.appcompat)
    implementation(libs.material)
}