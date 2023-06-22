plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
}

android {
    namespace = "ir.beigirad.challenge.transferlist"
    resourcePrefix = "transfer_list_"


    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:theme"))

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.viewBindingKtx)
}