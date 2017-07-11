package com.cleo.rest.services.impl;

import com.cleo.rest.exceptions.InvalidIdException;
import com.cleo.rest.pojo.Car;
import com.cleo.rest.services.ICarsWebService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.ws.rs.WebApplicationException;
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
          .build(),
      new Car.Builder()
          .id(UUID.randomUUID())
          .make("Toyota")
          .model("Prius")
          .color("Red")
          .year(2011)
          .build()
  );


  // Go through error scenarios for different endpoints
  // bad id's -> null (making a class extending web application exception
  //
  // InvalidIdException
  // NotFoundExceptionClass (404) -> same package as badRequest

  // WebApplicationException status code and string message

  // How to handle bad input for filter & sort? Or even throw exception, since it will default?
  @Override
  public Response getAll(String filter, String sort) {

    if(filter.equals("make")) {
      Comparator<Car> byMake = Comparator.comparing(Car::getMake);
      if(sort.equals("asc")) {
        return Response.ok(
            cars.stream()
                .sorted(byMake)
                .collect(Collectors.toList())
        ).build();
      }
      else if(sort.equals("desc")) {
        return Response.ok(
            cars.stream()
                .sorted(byMake.reversed())
                .collect(Collectors.toList())
        ).build();
      }
    }

    else if(filter.equals("model")) {
      Comparator<Car> byModel = Comparator.comparing(Car::getModel);
      if(sort.equals("asc")) {
        return Response.ok(
            cars.stream()
                .sorted(byModel)
                .collect(Collectors.toList())
        ).build();
      }
      else if(sort.equals("desc")) {
        return Response.ok(
            cars.stream()
                .sorted(byModel.reversed())
                .collect(Collectors.toList())
        ).build();
      }
    }

    else if(filter.equals("color")) {
      Comparator<Car> byColor = Comparator.comparing(Car::getColor);
      if(sort.equals("asc")) {
        return Response.ok(
            cars.stream()
                .sorted(byColor)
                .collect(Collectors.toList())
        ).build();
      }
      else if(sort.equals("desc")) {
        return Response.ok(
            cars.stream()
                .sorted(byColor.reversed())
                .collect(Collectors.toList())
        ).build();
      }
    }

    else if(filter.equals("year")) {
      Comparator<Car> byYear = Comparator.comparing(Car::getYear);
      if(sort.equals("asc")) {
        return Response.ok(
            cars.stream()
                .sorted(byYear)
                .collect(Collectors.toList())
        ).build();
      }
      else if(sort.equals("desc")) {
        return Response.ok(
            cars.stream()
                .sorted(byYear.reversed())
                .collect(Collectors.toList())
        ).build();

      }
    }

    return Response.ok(
        cars
    ).build();
  }



  // private helper getCar
  private Car findCar(String id) {

    if (!(cars.stream()
          .filter(car -> UUID.fromString(id).equals(car.getId()))
          .findFirst())
        .isPresent()) {
      return null;
    }

    return cars.stream()
        .filter(car -> UUID.fromString(id).equals(car.getId()))
        .findFirst()
        .get();
  }



  /*
      Exception cases:
        -User can enter an invalid id (404)
        -User can enter a null id, or a bad request (400)
     */
  @Override
  public Response getCarById(String id) {

    if(Strings.isNullOrEmpty(id)) {
      throw new InvalidIdException("ID cannot be null or empty", Response.Status.BAD_REQUEST);
    }
    try{
      UUID.fromString(id);
    }catch(Exception ex){
      throw new InvalidIdException("ID is not a valid UUID", Response.Status.BAD_REQUEST);
    }

    Car myCar = findCar(id);
    if(myCar == null) {
      throw new InvalidIdException("No car with that ID", Response.Status.NOT_FOUND);
    }

    return Response.ok(
      myCar
    ).build();
  }


  /*
    Exception cases:
      -User enters a null car (400)
   */
  @Override
  public Response addCar(Car car) throws Exception{
    car.setId(UUID.randomUUID());
    cars.add(car);
    return Response.created(new URI("http://localhost:8080/api/cars/" + car.getId())).build();
  }



  /*
    Exception cases:
      -User can enter an invalid id (404)
      -User can enter a null id, or a bad request (400)
   */
  @Override
  public Response deleteCarById(String id) {
    Car myCar = cars.stream()
        .filter(car -> UUID.fromString(id).equals(car.getId()))
        .findFirst()
        .get();

    return Response.ok(
        cars.remove(myCar) + ", " + myCar.getYear() + " " + myCar.getColor() + " " + myCar.getMake() + " " + myCar.getModel() + " has been deleted!"
    ).build();
  }



  /*
    Exception cases:
      -User can enter an invalid id (404)
      -User can enter a null id, or a bad request (400)
   */
  @Override
  public Response purchaseCarById(String id) {
    Car myCar = cars.stream()
        .filter(car -> UUID.fromString(id).equals(car.getId()))
        .findFirst()
        .get();


    return Response.ok(
        cars.remove(myCar) + ", " + myCar.getYear() + " " + myCar.getColor() + " " + myCar.getMake() + " " + myCar.getModel() + " has been purchased!"
    ).build();
  }



  /*
    Exception cases:
      -User can enter an invalid id (404)
      -User can enter a null id, or a bad request (400)
      -user can enter a null car (400)
   */
  @Override
  public Response updateCarById(String id, Car car) {

    Car myCar = cars.stream()
        .filter(c -> UUID.fromString(id).equals(c.getId()))
        .findFirst()
        .get();

    myCar.setMake(car.getMake());
    myCar.setModel(car.getModel());
    myCar.setColor(car.getColor());
    myCar.setYear(car.getYear());

    return Response.ok(
        myCar
    ).build();
  }

  // WON'T NEED THIS IN THE FUTURE
  @Override
  public Response orderCarsByYear() {

    Comparator<Car> byYear = Comparator.comparing(Car::getYear);

    return Response.ok(
        cars.stream()
            .sorted(byYear)
            .collect(Collectors.toList())
    ).build();
  }


  // If I want to return a list in a different order, take in whatever param to be specified (color),
  // using stream, filter to match the param, and return that new filtered list
}