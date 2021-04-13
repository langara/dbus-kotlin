plugins {
    java
    id("pl.mareklangiewicz.deps")
}

group = DBusKotlinKonf.group
version = DBusKotlinKonf.verStr

repositories {
    mavenCentral()
}

dependencies {
    implementation(Deps.dbusJava)
}

