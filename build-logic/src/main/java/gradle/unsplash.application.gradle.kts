import plugin.configureAndroid
import plugin.configureAndroidTest
import plugin.configureCompose
import plugin.configureHilt
import plugin.configureKotlinTest
import plugin.configurePagingCommon
import plugin.configureRoom

plugins {
    id("com.android.application")
}

configureAndroid()
configureAndroidTest()
configureKotlinTest()
configureHilt()
configureCompose()
configureRoom()
configurePagingCommon()