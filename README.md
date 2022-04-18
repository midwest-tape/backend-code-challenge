# Backend Code Challenge

A coding challenge built with spring boot to make the code challenge self-contained.

This challenge requires Java 11 to be installed.

## Checking Out Code

1. Fork the https://github.com/midwest-tape/backend-code-challenge repository into your own account.
2. Afterwards clone your forked repository as normal to your local machine.
3. From the `main` branch create a branch named `challenge-changes`. This will contain the various changes for the
   challenges.
4. Make sure you are on the `challenge-changes` branch before making code changes.

## Running Application

___
To run the application:

    ./mvnw spring-boot:run

Application runs at:

    http://localhost:8080

H2 In-Memory Database Console is at:

    http://localhost:8080/h2-console

## Challenge 1

___
Create an Address table and associate it with the two users

Using the migration script `src/main/resources/db/migration/V1__init_db.sql` perform the following:

* Create an Address table with the following fields
    * id primary key not null auto increment
    * address1 - varchar 255 not null
    * address2 - varchar 255
    * city - varchar 255 not null
    * state - varchar 100 not null
    * postal - varchar 10 not null
* Populate the new Address table with 2 records
* A user can only have one address associated with it. Update the init script to associate the two new addresses with
  the two different users. Only the user requires an addressId foreign key.

## Challenge 2

___
Return the Address as part of the User from the `GET /v1/users/{userId}` RESTful endpoint.

## Challenge 3

___
Using the `src/main/resources/schema/schema.graphqls` write out a graphql schema that would query a User and its
associated Address using the user id.

## Challenge 4

___
Add a RESTful compliant endpoint for updating a User. This should update the user in the database.

## Submit PR

Using Github submit a PR for your `challenge-changes` branch to the
original https://github.com/midwest-tape/backend-code-challenge repository.

