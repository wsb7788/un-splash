import extention.implementation
import plugin.configureAndroid
import plugin.configureAndroidTest
import plugin.configureInject
import plugin.configureKotlin
import plugin.configureKotlinTest
import plugin.configurePagingCommon
import plugin.configureRoom
import plugin.configureRoomCommon

configureKotlin()
configureRoomCommon()
configureKotlinTest()
configurePagingCommon()
configureInject()

dependencies {
    implementation(project(":data:model"))
    implementation(project(":domain:entity"))
}