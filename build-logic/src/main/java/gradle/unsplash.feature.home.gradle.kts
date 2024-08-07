import plugin.configureAndroid
import plugin.configureAndroidTest
import plugin.configureCoil
import plugin.configureCompose
import plugin.configureFeature
import plugin.configureHilt
import plugin.configureNavigation
import plugin.configurePagingCompose

plugins {
    id("com.android.library")
}

configureAndroid()
configureAndroidTest()
configureCompose()
configureHilt()
configureNavigation()
configureFeature()
configurePagingCompose()
configureCoil()

dependencies {
    implementation(project(":common:designsystem"))
}