#Java8 For Hipsters

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

## Functional programming

It's finally time for monads in Java (those are crazy time, indeed). Entering the container `Optional<T>`, that can wrap a `null` value or otherwise.

    Optional<String> some = Optional.of("value");
    some.get(); // returns "value"
    some.isPresent(); // returns true

Even with this container class, it is still not safe to pass `null` around using the `get()` method:

    Optional<String> none = Optional.ofNullable(null); // Optional.of(null) throws a NPE
    none.get(); // throws NoSuchElementException

Monads? We don't need monads where we're going!! I'm no category theory guru, but with `Optional` value is yet possible
to get rid of all ugly `null` checks in the code.
It looks something like this:

    Optional<Integer> someLen = Optional.of(1000L)
            .flatMap(id -> Optional.ofNullable(fakeNameLookup(id)))
            .flatMap(name -> Optional.ofNullable(name.length()));

Unless one of the function called by `flatMap` returns a `null` we will be fine.

### References
* __Cay S. Horstmann__ (2014); `Java SE 8 for the Really Impatient`; Addison-Wesley Professional; 1st edition
* __Raoul-Gabriel Urma, Mario Fusco, and Alan Mycroft__ (2014); `Java 8 in Action`; Manning Pubblishing; MEAP