Java8 for hipsters
==================

[![Build Status](https://travis-ci.org/CarloMicieli/java8-for-hipsters.png?branch=master)](https://travis-ci.org/CarloMicieli/java8-for-hipsters)

The project includes `Gradle` build script. As `JDK 1.8.0` is not the default `JDK`, it is needed to pass the installation path to `Gradle`.

To run the tests on Linux:

    $ export JDK8_HOME=/usr/lib/jvm/java-8-rc1-oraclejdk-amd64
    $ ./gradlew -Dorg.gradle.java.home=$JDK8_HOME check

To run the tests on Windows:

    $ set JDK8_HOME="C:\Program Files\Java\jdk1.8.0"
    $ gradlew -Dorg.gradle.java.home=%JDK8_HOME% check

The samples are built against `JDK 1.8.0 rc1`.


