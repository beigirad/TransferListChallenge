import org.codehaus.groovy.runtime.ProcessGroovyMethods

plugins {
    id(libs.plugins.androidApplication.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
}

android {
    namespace = "ir.beigirad.challenge"

    defaultConfig {
        applicationId = "ir.beigirad.challenge"

        val gitSha = "git rev-parse --short HEAD".execute().text().trim()
        versionCode = 1
        versionName = "0.1-$gitSha"

        setProperty("archivesBaseName", "challenge-v$versionName")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:theme"))
    implementation(project(":core:data"))
    implementation(project(":features:transferlist"))

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintLayout)
    implementation(libs.viewBindingKtx)

    implementation(libs.hilt)
    kapt(libs.hiltCompiler)
    implementation(libs.timber)

    testImplementation(libs.jUnit)
    androidTestImplementation(libs.androidJUnit)
    androidTestImplementation(libs.espresso)
}

fun String.execute() = ProcessGroovyMethods.execute(this)
fun Process.text(): String = ProcessGroovyMethods.getText(this)