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
import javax.ws.rs.core.Response;



/**
 * REST API exercise to perform CRUD operations on a list of cars.
 *
 *
 * @author agesmer
 * @since 7/10/2017
 */
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

  // How to handle bad input for filter & sort? Or even throw exception, since it will default?
  /**
   * Gets all cars. Default display is in ascending order of when cars were added to list. Optional query params are
   * filter and sort.
   *
   *
   * @param filter -> List cars by a specific attribute (id, make, model, color, year).
   * @param sort -> Specify order of cars based on filter to be in ascending (asc) or descending (desc) order.
   * @return -> Display of cars based on user's query parameters.
   */
  @Override
  public Response getAll(String filter, String sort) {

    if(filter.equals("id")) {
      Comparator<Car> byId = Comparator.comparing(Car::getId);
      if(sort.equals("asc")) {
        return Response.ok(
            cars.stream()
                .sorted(byId)
                .collect(Collectors.toList())
        ).build();
      }
      else if(sort.equals("desc")) {
        return Response.ok(
            cars.stream()
                .sorted(byId.reversed())
                .collect(Collectors.toList())
        ).build();
      }
    }

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

    if(filter.equals("model")) {
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

    if(filter.equals("color")) {
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

    if(filter.equals("year")) {
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

  /***
   * Finds specific car based on id passed in.
   *
   * @param id
   * @return Car specified from id, if no car has such id, returns NULL.
   */
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


  /***
   * Displays car specified by id.
   *
   * @param id
   * @return -> Display of car based on id.
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


  /***
   * Adds a new car to the list of cars.
   *
   * @param car
   * @return -> Response verifying that a car has been added to list of cars.
   * @throws Exception
   */
  @Override
  public Response addCar(Car car) throws Exception{
    car.setId(UUID.randomUUID());
    cars.add(car);
    return Response.created(new URI("http://localhost:8080/api/cars/" + car.getId())).build();
  }


  /***
   * Deletes car from list of cars as specified by id.
   *
   * @param id
   * @return -> Response verifying that the car has been deleted from list of cars.
   */
  @Override
  public Response deleteCarById(String id) {

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
        cars.remove(myCar) + ", " + myCar.getYear() + " " + myCar.getColor() + " " + myCar.getMake() + " "
            + myCar.getModel() + " has been deleted!"
    ).build();
  }


  /***
   * Purchases car as specified from id, removing the car from the list of cars.
   *
   * @param id
   * @return -> Response that car has been purchased and removed from list of cars.
   */
  @Override
  public Response purchaseCarById(String id) {

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
        cars.remove(myCar) + ", " + myCar.getYear() + " " + myCar.getColor() + " " + myCar.getMake() + " "
            + myCar.getModel() + " has been purchased!"
    ).build();
  }


  /***
   * Updates car by id, taking in a car with desired attributes to be updated.
   *
   * @param id
   * @param car
   * @return -> Returns the updated car as specified by id.
   */
  @Override
  public Response updateCarById(String id, Car car) {

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

    myCar.setMake(car.getMake());
    myCar.setModel(car.getModel());
    myCar.setColor(car.getColor());
    myCar.setYear(car.getYear());

    return Response.ok(
        myCar
    ).build();
  }
}