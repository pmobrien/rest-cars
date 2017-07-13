package com.cleo.rest.services.impl;

import com.cleo.rest.exceptions.InvalidIdException;
import com.cleo.rest.pojo.Car;
import com.cleo.rest.services.ICarsWebService;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
//import jdk.internal.util.xml.impl.Input;

import java.net.URI;
import java.util.*;
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

  private final static Map<String, Comparator<Car>> map = new HashMap<String, Comparator<Car>>() {{
    put("id", Comparator.comparing(Car::getId));
    put("make", Comparator.comparing(Car::getMake));
    put("model", Comparator.comparing(Car::getModel));
    put("color", Comparator.comparing(Car::getColor));
    put("year", Comparator.comparing(Car::getYear));
  }};

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

    // Ascending order:
    if(sort.equals("asc")) {
      return Response.ok(
          cars.stream()
              .sorted(map.get(filter))
              .collect(Collectors.toList())
      ).build();
    } // Descending order:
    else if(sort.equals("desc")) {
      return Response.ok(
          cars.stream()
              .sorted(map.get(filter).reversed())
              .collect(Collectors.toList())
      ).build();
    }

    return Response.ok(
        cars
    ).build();
  }

  /***
   * Throws appropriate error corresponding to invalid id (null, invalid UUID, or no car with id).
   * @param id
   */
  @VisibleForTesting
  protected void validateId(String id) {
    if(Strings.isNullOrEmpty(id)) {
      throw new InvalidIdException("ID cannot be null or empty", Response.Status.BAD_REQUEST);
    }
    try{
      UUID.fromString(id);
    }catch(Exception ex){
      throw new InvalidIdException("ID is not a valid UUID", Response.Status.BAD_REQUEST);
    }
    if(findCar(id) == null) {
      throw new InvalidIdException("No car with that ID", Response.Status.NOT_FOUND);
    }
  }

  /***
   * Finds specific car based on id passed in.
   *
   * @param id
   * @return Car specified from id, if no car has such id, returns NULL.
   */
  @VisibleForTesting
  protected Car findCar(String id) {
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

    validateId(id);

    return Response.ok(
        findCar(id)
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


  public String toString(Car car) {
    return car.getYear() + " " + car.getColor() + " " + car.getMake() + " " + car.getModel();
  }

  /***
   * Deletes car from list of cars as specified by id.
   *
   * @param id
   * @return -> Response verifying that the car has been deleted from list of cars.
   */
  @Override
  public Response deleteCarById(String id) {

    validateId(id);

    Car myCar = findCar(id);

    cars.remove(myCar);

    return Response.ok(

        toString(myCar) + " has been deleted!"
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

    validateId(id);

    Car myCar = findCar(id);

    cars.remove(myCar);

    return Response.ok(
        toString(myCar) + " has been purchased!"
    ).build();
  }

  // MOVE THIS OVER TO CAR, AS WELL AS TOSTRING()

  private Car updateCar(Car oldCar, Car newCar) {

    oldCar.setMake(newCar.getMake());
    oldCar.setModel(newCar.getModel());
    oldCar.setColor(newCar.getColor());
    oldCar.setYear(newCar.getYear());

    return oldCar;
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

    validateId(id);

    return Response.ok(
        updateCar(findCar(id), car)
    ).build();
  }

  @Override
  public Response patchCarById(String id, Car car) {

    car.setId(UUID.randomUUID());

    validateId(id);

    /*
    // Should check if any fields are null... then that means don't update it?
    if(car.getMake() != null) {
      myCar.setMake(car.getMake());
    }
    if(car.getModel() != null) {
      myCar.setModel(car.getModel());
    }
    if(car.getColor() != null) {
      myCar.setColor(car.getColor());
    }
    if((Integer)car.getYear() != null) {
      myCar.setYear(car.getYear());
    }
    */

    return Response.ok(
        car
    ).build();

  }
}
