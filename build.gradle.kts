buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Deps.kotlinGradlePlugin)
    }
}

version = DBusKotlinKonf.verStr
group = DBusKotlinKonf.group
description = DBusKotlinKonf.description

allprojects {
    repositories {
        mavenCentral()
        maven(Repos.jitpack)
    }
}
