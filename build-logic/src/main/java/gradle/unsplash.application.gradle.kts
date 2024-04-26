import plugin.configureAndroid
import plugin.configureAndroidTest
import plugin.configureCompose
import plugin.configureHilt
import plugin.configureRoom

plugins {
    id("com.android.application")
}

configureAndroid()
configureAndroidTest()
configureHilt()
configureCompose()
configureRoom()