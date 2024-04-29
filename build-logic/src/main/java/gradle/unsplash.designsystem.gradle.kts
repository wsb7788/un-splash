import plugin.configureAndroid
import plugin.configureAndroidTest
import plugin.configureCoil
import plugin.configureCompose

plugins {
    id("com.android.library")
}

configureAndroid()
configureAndroidTest()
configureCompose()
configureCoil()