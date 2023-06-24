plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
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
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:data"))

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.viewModelKtx)
    implementation(libs.lifecycleKtx)
    implementation(libs.fragmentKtx)
    implementation(libs.material)
    implementation(libs.recyclerView)
    implementation(libs.shimmer)
    implementation(libs.viewBindingKtx)

    implementation(libs.hilt)
    kapt(libs.hiltCompiler)
}