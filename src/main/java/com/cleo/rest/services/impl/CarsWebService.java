package com.cleo.rest.services.impl;

import com.cleo.rest.pojo.Car;
import com.cleo.rest.services.ICarsWebService;
import com.google.common.collect.Lists;
import java.util.List;
import javax.ws.rs.core.Response;

public class CarsWebService implements ICarsWebService {

  // Don't worry about persistin anything, just do CRUD operations on this list
  private final static List<Car> cars = Lists.newArrayList();
  
  @Override
  public Response getAll() {
    return Response.ok(new Car()).build();
  }
}
