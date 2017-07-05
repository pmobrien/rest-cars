package com.cleo.rest.pojo;

import java.util.UUID;

public class Car {

  // private fields for a car:
  private UUID id;
  private String make;
  private String model;
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

  public void setYear(int year) {
    this.year = year;
  }

  



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

    public Builder year(int year) {
      car.year = year;
      return this;
    }

    public Car build() {
      return car;
    }
  }
}



















