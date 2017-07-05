package com.cleo.rest.services.impl;

import com.cleo.rest.pojo.Car;
import com.cleo.rest.services.ICarsWebService;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.core.Response;

public class CarsWebService implements ICarsWebService {

  // Don't worry about persisting anything, just do CRUD operations on this list.
  private final static List<Car> cars = Lists.newArrayList(
      new Car.Builder()
          .id(UUID.randomUUID())
          .build(),
      new Car.Builder()
          .id(UUID.randomUUID())
          .build()
  );
  
  @Override
  public Response getAll() {
    return Response.ok(
        new Car.Builder()
            .id(UUID.randomUUID())
            .build()
    ).build();
  }
}
