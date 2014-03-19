#Java8 For Hipsters

[![Build Status](https://travis-ci.org/CarloMicieli/java8-for-hipsters.png?branch=master)](https://travis-ci.org/CarloMicieli/java8-for-hipsters)

The project includes `Gradle` build script.

To run the test suite:

    $ git clone https://github.com/CarloMicieli/java8-for-hipsters.git
    $ ./gradlew check

To import the project into `Intellij IDEA`:
    
    $ ./gradlew idea

The samples are built against `JDK 1.8.0`.

    $ java -version
    java version "1.8.0"
    Java(TM) SE Runtime Environment (build 1.8.0-b132)
    Java HotSpot(TM) 64-Bit Server VM (build 25.0-b70, mixed mode)

## Functional programming

### Optional
It's finally time for monads in Java (those are crazy times, indeed). Entering the container `Optional<T>`, that can wrap a `null` value or otherwise.

    Optional<String> some = Optional.of("value");
    some.get(); // returns "value"
    some.isPresent(); // returns true

Even with this container class, it is still not safe to pass `null` around using `Optional` objects:

    Optional<String> none = Optional.ofNullable(null); // Optional.of(null) throws a NPE
    none.get(); // throws NoSuchElementException

Monads? We don't need monads where we're going!! I'm no category theory guru, but thanks to the brand new `Optional` class is finally possible to get rid of all those ugly `null` checks in the code.
It looks something like this:

    Optional<Integer> someLen = Optional.of(1000L)
            .flatMap(id -> fakeNameLookup(id))
            .map(name -> name.length());

Unless one of the function called by `flatMap` returns a `null` we are totally safe.

### Functions, Functions

Java designers are famous to be truly traditional people. Functions in java 8 are somehow syntax sugar for functional interfaces (we called SAM, "__single
abstract method__" back in the old times...) so we won't have high order functions with java 8. Still, a huge step forward.

We create functions using lambda expressions:

    Function<String, String> upperString = String::toUpperCase;
    assert(upperString.apply("hello") == "HELLO");
    BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
    assert(sum.apply(2, 2) == 4);

Other than creating functions, Java 8 will allow us to compose functions:

    Function<String, Integer> charsNum = s -> s.replace(" ", "").length();
    Function<Integer, Integer> doubled = n -> n * 2;
    Function<String, Integer> composed = charsNum.andThen(doubled);
    assert(composed.apply("hello world") == 20);

After function we have `Predicate<T>', they perform a test that produces a boolean result.

    Predicate<String> longString = s -> s.length() > 10;
    assert(longString.test("hello") == false);

### Father time

Time and data api has just got big time changes.

    LocalDate now = LocalDate.now();



## References
* __Cay S. Horstmann__ (2014); `Java SE 8 for the Really Impatient`; Addison-Wesley Professional; 1st edition
* __Raoul-Gabriel Urma, Mario Fusco, and Alan Mycroft__ (2014); `Java 8 in Action`; Manning Pubblishing; MEAP

Inspired by the following blog posts:

* __Michael Scharhag__ [A deeper look into the Java 8 Date and Time API](http://www.mscharhag.com/2014/02/java-8-datetime-api.html "A deeper look into the Java 8 Date and Time API")
