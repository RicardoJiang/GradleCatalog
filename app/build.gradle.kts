plugins {
    id("com.android.application")
    id("kotlin-android")
    id("version-catalog")
    id("maven-publish")
}

android {
    compileSdk = libs.versions.compilesdk.get().toInt()

    defaultConfig {
        applicationId = "com.zj.gradlecatalog"
        minSdk = 21
        targetSdk = libs.versions.targetsdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation(libs.retrofit)
}

catalog {
    // declare the aliases, bundles and versions in this block
    versionCatalog {
        from(files("../libs.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.zj.catalog"
            artifactId = "catalog"
            version = "1.0.0"
            from(components["versionCatalog"])
        }
    }
}