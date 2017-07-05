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
          .make("Toyota")
          .model("Camry")
          .year(2010)
          .build(),
      new Car.Builder()
          .id(UUID.randomUUID())
          .make("Honda")
          .model("CRV")
          .year(2007)
          .build(),
      new Car.Builder()
          .id(UUID.randomUUID())
          .make("Buick")
          .model("Encore")
          .year(2017)
          .build(),
      new Car.Builder()
          .id(UUID.randomUUID())
          .make("Buick")
          .model("Enclave")
          .year(2013)
          .build()
  );
  
  @Override
  public Response getAll() {

    return Response.ok(
        cars
    ).build();
  }
}



