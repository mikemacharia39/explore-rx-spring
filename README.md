# Explore Reactive spring boot

## Why reactive spring boot

| Reactive spring boot                                                    | Traditional restful apis                                                                  |
|-------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| Asynchronous and non blocking                                           | Synchronous and blocking                                                                  |
| Threads are reused                                                      | Has a defined number of thread i.e. 200, adding more threads also has an impact on memory |
| Uses events                                                             | Uses thread per request model                                                             |
| There are mechanisms to handle back pressure                            | There is no support back pressure                                                         |
| There is currently no support for relational databases only nosql support | There is robust support for relational databases                                          |
| Uses netty - netty is a non blocking server                             | Mostly uses tomcat, but there is support for other servers                                |


### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/3.4.1/reference/web/reactive.html)
* [Spring Data Reactive MongoDB](https://docs.spring.io/spring-boot/3.4.1/reference/data/nosql.html#data.nosql.mongodb)

### Definitions
1. Flux - is a representation of 0 to n elements
2. Mono - is a representation of 0 or 1 element

### 2 ways you can implement reactive spring endpoints:
1. Using the `@RestController` annotation - the usual way
2. Using the `RouterFunction` and `HandlerFunction` - the functional way