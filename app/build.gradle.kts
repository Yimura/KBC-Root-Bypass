plugins {
    alias(libs.plugins.android.application)
}

android {
    signingConfigs {
        create("release") {
            storeFile =
                file("../keystore/keystore.jks")
            storePassword = System.getenv("RELEASE_KEYSTORE_PASSWORD")
            keyAlias = System.getenv("RELEASE_KEYSTORE_ALIAS")
            keyPassword = System.getenv("RELEASE_KEY_PASSWORD")
        }
    }
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
            signingConfig = signingConfigs.getByName("release")
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