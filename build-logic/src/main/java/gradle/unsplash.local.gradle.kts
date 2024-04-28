import extention.implementation
import plugin.configureInject
import plugin.configureKotlin
import plugin.configurePagingCommon
import plugin.configureRoomCommon

configureKotlin()
configureRoomCommon()
configurePagingCommon()
configureInject()

dependencies {
    implementation(project(":data:model"))
    implementation(project(":domain:entity"))
}