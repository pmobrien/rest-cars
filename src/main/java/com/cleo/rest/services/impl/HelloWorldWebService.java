package com.cleo.rest.services.impl;

import com.cleo.rest.services.IHelloWorldWebService;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HelloWorldWebService implements IHelloWorldWebService {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response helloWorld() {
    return Response.ok("Hello World").build();
  }
}
