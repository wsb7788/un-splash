import extention.implementation
import org.gradle.kotlin.dsl.project
import plugin.configureAndroid
import plugin.configureInject
import plugin.configureKotlin
import plugin.configureRoom
import plugin.configureRoomCommon

configureKotlin()
configureRoomCommon()
configureInject()

dependencies {
    implementation(project(":data:model"))
}