# resilience4j-demo

* Exposes 3 endpoints:

  1. http://localhost:8080/test/fast (Immediate Success Response)
  2. http://localhost:8080/test/slow (Success Response With 5sec Delay)
  3. http://localhost:8080/test/exception (500 Response)

* Calls another service running at port 9999 from the [node-app](https://github.com/rishabhsairawat/node-app)

* Circuit breaker metrics can be checked at http://localhost:8090/actuator/health
