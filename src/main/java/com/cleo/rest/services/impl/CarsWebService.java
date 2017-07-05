package com.cleo.rest.services.impl;

import com.cleo.rest.pojo.Car;
import com.cleo.rest.services.ICarsWebService;
import com.google.common.collect.Lists;

import java.net.URI;
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
          .color("Green")
          .year(2010)
          .build(),
      new Car.Builder()
          .id(UUID.randomUUID())
          .make("Honda")
          .model("CRV")
          .color("Purple")
          .year(2007)
          .build(),
      new Car.Builder()
          .id(UUID.randomUUID())
          .make("Buick")
          .model("Encore")
          .color("Black")
          .year(2017)
          .build(),
      new Car.Builder()
          .id(UUID.randomUUID())
          .make("Buick")
          .model("Enclave")
          .color("Blue")
          .year(2013)
          .build()
  );






  @Override
  public Response getAll() {
    return Response.ok(
        cars
    ).build();
  }

  @Override
  public Response getCarById(String id) {
    Car myCar = cars.stream()
        .filter(car -> UUID.fromString(id).equals(car.getId()))
        .findFirst()
        .get();

    return Response.ok(
      myCar
    ).build();
  }

  @Override
  public Response addCar(Car car) throws Exception{
    car.setId(UUID.randomUUID());
    cars.add(car);
    return Response.created(new URI("http://localhost:8080/api/cars/" + car.getId())).build();
  }


}



