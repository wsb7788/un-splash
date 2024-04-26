import extention.implementation
import plugin.configureKotlin
import plugin.configureKtor
import plugin.configurePagingCommon

configureKotlin()
configurePagingCommon()
configureKtor()

dependencies {
    implementation(project(":data:model"))
}