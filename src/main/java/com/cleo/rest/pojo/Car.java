package com.cleo.rest.pojo;

import java.util.UUID;

public class Car {

  // private fields for a car:
  private UUID id;
  private String make;
  private String model;
  private String color;
  private int year;


  // Getter methods:
  public UUID getId() {
    return this.id;
  }

  public String getMake() {
    return this.make;
  }

  public String getModel() {
    return this.model;
  }

  public String getColor() {
    return this.color;
  }

  public int getYear() {
    return this.year;
  }



  // Setter methods:
  public void setId(UUID id) {
    this.id = id;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setYear(int year) {
    this.year = year;
  }

  // Other useful methods:

  // PUT
  public Car updateCar(Car newCar) {

    this.setId(newCar.getId());
    this.setMake(newCar.getMake());
    this.setModel(newCar.getModel());
    this.setColor(newCar.getColor());
    this.setYear(newCar.getYear());

    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Car)) return false;

    Car car = (Car) o;

    if (getYear() != car.getYear()) return false;
    if (!getId().equals(car.getId())) return false;
    if (!getMake().equals(car.getMake())) return false;
    if (!getModel().equals(car.getModel())) return false;
    return getColor().equals(car.getColor());
  }



  @Override
  public int hashCode() {
    int result = getId().hashCode();
    result = 31 * result + getMake().hashCode();
    result = 31 * result + getModel().hashCode();
    result = 31 * result + getColor().hashCode();
    result = 31 * result + getYear();
    return result;
  }


  public void patchCar(Car oldCar, Car newCar) {

    if(newCar.getMake() != null) {
      oldCar.setMake(newCar.getMake());
    }
    if(newCar.getModel() != null) {
      oldCar.setModel(newCar.getModel());
    }
    if(newCar.getColor() != null) {
      oldCar.setColor(newCar.getColor());
    }
    if((Integer)newCar.getYear() != null) {
      oldCar.setYear(newCar.getYear());
    }
  }

  public String toString() {
    return getYear() + " " + getColor() + " " + getMake() + " " + getModel();
  }


  // Builder class:

  public static class Builder {
    
    private final Car car = new Car();
    
    public Builder id(UUID id) {
      car.id = id;
      return this;
    }

    public Builder make(String make) {
      car.make = make;
      return this;
    }

    public Builder model(String model) {
      car.model = model;
      return this;
    }

    public Builder color(String color) {
      car.color = color;
      return this;
    }

    public Builder year(int year) {
      car.year = year;
      return this;
    }

    public Car build() {
      return car;
    }
  }
}



















