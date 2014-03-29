#Java8 For Hipsters

[![Build Status](https://travis-ci.org/CarloMicieli/java8-for-hipsters.png?branch=master)](https://travis-ci.org/CarloMicieli/java8-for-hipsters)

The project includes a `Gradle` build script.
Let's start cloning the repo:

    [carlo:~] $ git clone https://github.com/CarloMicieli/java8-for-hipsters.git

To run the sample web application:
    
    [carlo:~] $ ./gradlew run

The web server is listening at `http://localhost:4567`.

To run the unit tests suite:

    [carlo:~] $ ./gradlew check

To generate the project files for `Intellij IDEA`:

    [carlo:~] $ ./gradlew idea

The samples are built against the latest `JDK 1.8.0`.

    [carlo:~] $ java -version
    java version "1.8.0"
    Java(TM) SE Runtime Environment (build 1.8.0-b132)
    Java HotSpot(TM) 64-Bit Server VM (build 25.0-b70, mixed mode)

The project includes few sample data sets, they are loading the data from `JSON` files under `/src/resources/data`.
The data are used through Java `stream`s:

    Teams.stream();     // yield a Stream<Team>...... NFL teams
    Stadiums.stream();  // yield a Stream<Stadium>... NFL stadiums
    States.stream();    // yield a Stream<State>..... USA states 

The project depends on the following packages:
    * `Google GSON`
    * `Spark micro web framework`
    * `JUnit` and `Hamcrest` for testing

## Everything is an API nowadays

`GET /stadiums`: the NFL stadiums list:

    [carlo:~] $ http http://localhost:4567/stadiums
    HTTP/1.1 200 OK
    Content-Length: 2020
    Content-Type: application/json
    Server: Jetty(9.0.2.v20130417)

    [
        {
            "capacity": 75000, 
            "id": 1, 
            "location": "Santa Clara", 
            "name": "Levis Stadium", 
            "openedYear": 2014, 
            "roofType": "Open", 
            "state": "California", 
            "surface": "419 Tifway Bermuda Grass", 
            "teamNames": [
                "San Francisco 49ers"
            ]
        }, 
        ....
    ]

`GET /stadiums/:stadiumId`: the information about the stadium with the `stadiumId`:

    [carlo:~] $ http http://localhost:4567/stadiums/1
    HTTP/1.1 200 OK
    Content-Length: 203
    Content-Type: application/json
    Server: Jetty(9.0.2.v20130417)

    {
        "capacity": 75000, 
        "id": 1, 
        "location": "Santa Clara", 
        "name": "Levis Stadium", 
        "openedYear": 2014, 
        "roofType": "Open", 
        "state": "California", 
        "surface": "419 Tifway Bermuda Grass", 
        "teamNames": [
            "San Francisco 49ers"
        ]
    }

`GET /teams/`: the NFL teams list:

    [carlo:~] $ http http://localhost:4567/teams
    HTTP/1.1 200 OK
    Content-Length: 1067
    Content-Type: application/json
    Server: Jetty(9.0.2.v20130417)

    [
        {
            "conference": "NFC", 
            "division": "North", 
            "foundedAt": 1900, 
            "id": 1, 
            "name": "Detroit Lions", 
            "shortName": "DET"
        }, 
        {
            "conference": "AFC", 
            "division": "South", 
            "foundedAt": 1900, 
            "id": 2, 
            "name": "Tennessee Titans", 
            "shortName": "TEN"
        }, 
        ....
    ]


`GET /teams/:teamId`: the information about the team with the `stadiumId`:

    [carlo:~] $ http http://localhost:4567/teams/1
    HTTP/1.1 200 OK
    Content-Length: 104
    Content-Type: application/json
    Server: Jetty(9.0.2.v20130417)

    {
        "conference": "NFC", 
        "division": "North", 
        "foundedAt": 1900, 
        "id": 1, 
        "name": "Detroit Lions", 
        "shortName": "DET"
    }


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
* __Benjamin Winterberg__ [Java 8 tutorial](http://winterbe.com/posts/2014/03/16/java-8-tutorial "Java 8 tutorial")
