plugins {
    id(libs.plugins.androidApplication.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
}

android {
    namespace = "ir.beigirad.challenge"

    defaultConfig {
        applicationId = "ir.beigirad.challenge"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintLayout)

    testImplementation(libs.jUnit)
    androidTestImplementation(libs.androidJUnit)
    androidTestImplementation(libs.espresso)
}