import extention.implementation
import plugin.configureInject
import plugin.configureKotlin
import plugin.configureKtor
import plugin.configurePagingCommon

configureKotlin()
configurePagingCommon()
configureKtor()
configureInject()

dependencies {
    implementation(project(":data:model"))
    implementation(project(":domain:entity"))
}