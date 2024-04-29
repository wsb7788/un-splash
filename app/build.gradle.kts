@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("unsplash.application")
}

android {
    namespace = "com.guesthouse.unsplash"

    defaultConfig {
        applicationId = "com.guesthouse.unsplash"
        versionCode = 1
        versionName = "1.0"
    }


    buildTypes {
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            isDebuggable = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isShrinkResources = false
            isMinifyEnabled = false
            isDebuggable = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":feature:home"))

    implementation(project(":domain:repository"))
    implementation(project(":domain:entity"))
    implementation(project(":data:repository"))
    implementation(project(":data:local"))
    implementation(project(":data:remote"))
    implementation(project(":data:model"))
    implementation(project(":core:navigation"))

    implementation(project(":common:designsystem"))
}