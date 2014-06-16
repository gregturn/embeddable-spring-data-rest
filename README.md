embeddable-spring-data-rest
===========================

An example of using Spring Data REST with an embeddable subtable

> **NOTE:** You HAVE to use Spring Data REST 2.1.0.RC1 for this to work properly. 2.0.x doesn't handle @Embeddable propertly. As a side effect, it's also recommend to upgrade Spring Data JPA to 1.6.0.RC1

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
