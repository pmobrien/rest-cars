A simple project to help developers get ramped up with REST.

Servlet implementation inspired by [this example](http://nikgrozev.com/2014/10/16/rest-with-embedded-jetty-and-jersey-in-a-single-jar-step-by-step/).

To run (from the project directory):

```
mvn clean install
java -jar target/rest-cars.jar
```

Once this is running, use Postman (or another REST client of your choice) to send a `GET` to `http://localhost:8080/hello-world`. If all is well, you should get back a `200` with `Hello World` in the body.