# spring-boot-actuator-custom
## Introduction
The Spring Boot Actuator, It provides several built-in endpoints and capabilities for monitoring and interacting with our application at runtime. For example 

`Health Endpoint`: It exposes information about the application's health, indicating whether the application is functioning correctly.

`Info Endpoint`: It provides custom information about the application, allowing you to expose arbitrary details such as version numbers, build information, etc.

`Metrics Endpoint`: It exposes a variety of metrics related to the application's performance, such as memory usage, garbage collection stats, and custom metrics.

or even create custom endpoints to expose additional information that we want, depending on our requirements. And, in this article, I'm going to provide some examples of custom endpoints and the important configuration that you need to know when working with actuators.


## Built-in Endpoints
The built-in endpoints are the endpoints that Spring Boot already provides for you. It's going to capture serveral aspects of information about your application for monitoring. To use built-in endpoints, it is straight-forward. You just need to define the endpoint that you want to expose in the `application. propertis` with the tag below.

`management.endpoints.web.exposure.include = endpointName1, endpointName2 `

For the lists of endpoints available, please see at -> [Actuator endpoints](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints)



[spring-boot-starter-actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)\
[Health Indicators in Spring Boot](https://www.baeldung.com/spring-boot-health-indicators)\
[Spring Boot Health actuator](https://springhow.com/spring-boot-health-check-indicators/)\
