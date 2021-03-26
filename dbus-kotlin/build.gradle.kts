plugins {
    kotlin("multiplatform")
    `maven-publish`
}

group = Konf.group
version = Konf.verStr

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(Deps.dbusJava)
//                implementation(Deps.dbusJavaOsgi)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Deps.dbusJavaUtils)
                implementation(kotlin("test-junit5"))
                implementation(Deps.junit5)
                runtimeOnly(Deps.junit5engine)
                runtimeOnly(Deps.uspek)
            }
        }
    }
}
