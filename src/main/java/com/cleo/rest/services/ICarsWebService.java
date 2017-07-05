package com.cleo.rest.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cars")
public interface ICarsWebService {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAll();


  @GET
  @Path("/{carId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCarById(@PathParam("carId") String id);
  


}
