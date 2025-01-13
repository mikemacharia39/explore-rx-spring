# Explore Reactive spring boot

## Why reactive spring boot

|-|-|
|Reactive spring boot|Traditional restful apis|
|-|-|
|Asynchronous and non blocking|Synchronous and blocking|
|Threads are reused|Has a defined number of thread i.e. 200, adding more threads also has an impact on memory|
|Uses events|Uses thread per request model|
|There are mechanisms to handle back pressure|There is no support back pressure|
|There is currently no support for relational databases only nosql support|There is robust support for relational databases|
