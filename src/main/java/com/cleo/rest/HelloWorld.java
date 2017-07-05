package com.cleo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello-world")
public class HelloWorld {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response helloWorld() {
    return Response.ok("Hello World").build();
  }
}
