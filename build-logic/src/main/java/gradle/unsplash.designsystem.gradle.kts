import plugin.configureAndroid
import plugin.configureAndroidTest
import plugin.configureCoil
import plugin.configureCompose
import plugin.configureFeature

plugins {
    id("com.android.library")
}

configureAndroid()
configureAndroidTest()
configureCompose()
configureCoil()