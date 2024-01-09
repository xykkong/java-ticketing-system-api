# java-ticketing-system-api

This project uses Java with [Quarkus](https://quarkus.io), the Supersonic Subatomic Java Framework.

## Description

This is a implementation of a ticketing system for a cinema. The core business logic is implemented by the getNextAvailableTicket API. 
- Assumes that we have a rectangle room of X rows and Y columns in each row (e.g. a simple rectangular layout). 
- Appplies social distance rules: there should be an empty place next to each occupant in all 4 directions.
The endpoint optionally accept a 'preferred seat':
-  If the preferred seat is provided, allocate the seat if it's available, or the 'next closest' seat if it's occupied (when looking for the next closest available seat, prefer the direction towards the middle of the cinema hall);
-  If the preferred seat is omitted, allocate the seat starting from the middle of the cinema hall.

## REQUIREMENTS

In order to run the app, you will need Graal VM 21 and docker installed.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

## Running the API

Booking without seat

```shell script
  http://localhost:8080/booking/ticket
```

Booking with seat. X and Y should be 1 <= x,y <= 100

```shell script
  http://localhost:8080/booking/ticket?x=5&y=3
```

## Running tests

```shell script
./gradlew test
```

You can then execute your native executable with: `./build/ticketing-system-1.0.0-SNAPSHOT-runner`

# Testing the API
List all seats
```
  http://localhost:8080/booking
```

Get next available seat
```
  http://localhost:8080/booking/ticket
```

# Considerations about scalability

- The design of this API was simple, However, I augmented the codebase with additional sections and comments to
  demonstrate how we can address potential future challenges.

## Think about how this can be scaled (and how scalability problems would be solved, like concurrency).

Within the code, I've integrated support for Optimistic Locking to handle potential concurrency issues that might arise
within the database. Another approach would be utilizing Pessimistic Locking. While it doesn't address concurrency in
the same way as Optimistic Locking, it aims to provide users with a better experience, aligning with the "First come,
first served" principle.

## What modifications would be required if we have 1k theaters, 5 halls each?

I have included additional code to showcase the structure of my models. Regarding performance, there might be a need to
reorganize our data. Here are some ideas:

- Implement a multitenancy architecture, considering the creation of a separate application and database for each
  theater, especially for those experiencing heavier traffic.
- Optimize by establishing appropriate indices, partitioning the database, and considering archiving past dates if we
  allow bookings for different dates.
- Introduce caching mechanisms to alleviate the load on the database and enhance overall system performance.
