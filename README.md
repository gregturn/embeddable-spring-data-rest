embeddable-spring-data-rest
===========================

An example of using Spring Data REST with an embeddable subtable

> **NOTE:** This has been udpated to Spring Boot 1.1.1.RELEASE, which includes Spring Data REST 2.1.0.RELEASE and Spring Data JPA 1.6.0.RELEASE.

Run it by launching `mvn clean spring-boot:run`.

To poke at the sample data, in another shell, use `curl`.

```
$ curl localhost:8080/systems/1
{
  "name" : "router101",
  "dependencies" : [ {
    "description" : "WLAN"
  }, {
    "description" : "UPS"
  } ],
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/systems/1"
    }
  }
}
```

```
$ curl -X PATCH -H "Content-type:application/json" -d '{ "dependencies" : [{"description":"MUX", "target": "http://localhost:8080/systems/1"}]}' localhost:8080/systems/1
$ curl localhost:8080/systems/1
{
  "name" : "router101",
  "dependencies" : [ {
    "description" : "MUX"
  } ],
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/systems/1"
    }
  }
}
```

Looking at the outpout from the IDE, you can see it is clearly interacting with the two tables properly.
