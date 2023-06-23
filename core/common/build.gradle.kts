plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
}

android {
    namespace = "ir.beigirad.challenge.common"
}