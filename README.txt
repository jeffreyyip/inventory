How to BUILD:
mvn clean package

How to RUN:
java -jar target/inventory-0.0.1-SNAPSHOT.jar


Here are entry URLs:

For REST API with Hypermedia links (HATEOAS)
http://localhost:8080/
http://localhost:8080/product
http://localhost:8080/profile/product


For Web GUI:
http://localhost:8080/web

For in-memory datbase:
http://localhost:8080/h2-console
(url: jdbc:h2:mem:testdb )
(user "sa" without password)

ASSUMPTION:
Category and SubCategory would not changed much;
so Enum is used to represent the master source and used Enum String instead Ordinal in database;
if requirement is needed to update it by user, then Enum should be replaced by others data structure
and reading them from external datastore/file.

The possible Enum values are retrieved by the client using the Hypermedia links
(http://localhost:8080/profile/product)

Database is using as in-memory H2 database; other datastore could be used and be config.


LIMITATION:

As Enum is used, the value of the Cateory and SubCategory is CASE sensitive (all capital);
extra logic is needed to handle non-case sensitive, if requirement needed.

Due to time limit, GUI has been implemented as DEMO purpose for Listing and Creation only,
did not implement Update and Delete yet, GUI logic might not be in good coding style;
and other GUI framework could be used instead.

The meta link for Enum values can't be access outside the original server as the REST service;
due to the issue/bug for CORS setting  in SPRING-DATA-REST library.
(http://localhost:8080/profile/product)


