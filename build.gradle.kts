
plugins {
    id("java")
    id("application")
}

group = "com.Franz3"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
        url = uri("https://repo.clojars.org")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.api-client:google-api-client:1.23.0")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.23.0")
    implementation("com.google.apis:google-api-services-youtube:v3-rev222-1.25.0")
    implementation ("org.apache.httpcomponents:httpclient:4.5.14")
    implementation ("net.clojars.suuft:libretranslate-java:1.0.5")
    implementation("org.python:jython-slim:2.7.3")

}

tasks.test {
    useJUnitPlatform()
}