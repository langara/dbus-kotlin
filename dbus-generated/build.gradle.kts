plugins {
    java
}

group = DBusKotlinKonf.group
version = DBusKotlinKonf.verStr

repositories {
    mavenCentral()
}

dependencies {
    implementation(Deps.dbusJava)
}

