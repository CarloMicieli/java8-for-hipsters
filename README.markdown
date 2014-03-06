#Java8 for hipsters

[![Build Status](https://travis-ci.org/CarloMicieli/java8-for-hipsters.png?branch=master)](https://travis-ci.org/CarloMicieli/java8-for-hipsters)

The project includes `Gradle` build script. As `JDK 1.8.0` is not the default `JDK`, it is needed to pass the installation path to `Gradle`.

To run the tests on Linux:

    $ export JDK8_HOME=/usr/lib/jvm/java-8-rc1-oraclejdk-amd64
    $ ./gradlew -Dorg.gradle.java.home=$JDK8_HOME check

To run the tests on Windows:

    $ set JDK8_HOME="C:\Program Files\Java\jdk1.8.0"
    $ gradlew -Dorg.gradle.java.home=%JDK8_HOME% check

The samples are built against `JDK 1.8.0 rc1`.

    $ java -version
    java version "1.8.0"
    Java(TM) SE Runtime Environment (build 1.8.0-b128)
    Java HotSpot(TM) 64-Bit Server VM (build 25.0-b69, mixed mode)

## References
* Cay S. Horstmann; `Java SE 8 for the Really Impatient`; Addison-Wesley Professional; 1st edition, 2014;
* Raoul-Gabriel Urma, Mario Fusco, and Alan Mycroft; `Java 8 in Action`; Manning Pubblishing; MEAP, 2014.