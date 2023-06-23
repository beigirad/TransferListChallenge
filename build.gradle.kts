import com.android.build.gradle.TestedExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinKapt) apply false
    alias(libs.plugins.hilt) apply false
}

allprojects {
    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    afterEvaluate {
        extensions.findByType(TestedExtension::class)?.apply {
            setCompileSdkVersion(33)
            defaultConfig.apply {
                minSdk = 21
                targetSdk = 33
            }
        }
    }
}