package com.cleo.rest.services;
import com.cleo.rest.pojo.Car;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cars")
public interface ICarsWebService {

  // Default URL to get all cars in the list "cars"
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response getAll();

  // GETs a car as specified by an id (string)
  @GET
  @Path("/{carId}")
  @Produces(MediaType.APPLICATION_JSON)
  Response getCarById(@PathParam("carId") String id) throws Exception;

  // POSTs a new car to the list "cars"
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  Response addCar(Car car) throws Exception;

  // Delete a car from the list (DELETE)
  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{carId}")
  Response deleteCarById(@PathParam("carId") String id) throws Exception;

  // Purchases a car (DELETEs from the list "cars"
  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{carId}/buy")
  Response purchaseCarById(@PathParam("carId") String id) throws Exception;

  // Modify the information about a car (PUT)
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{carId}")
  Response updateCarById(@PathParam("carId") String id, Car updateCar) throws Exception;



















  // Get a specific car (maybe by color,




}
