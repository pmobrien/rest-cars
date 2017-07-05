package com.cleo.rest.pojo;

import java.util.UUID;

public class Car {

  private UUID id;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }
  
  public static class Builder {
    
    private final Car car = new Car();
    
    public Builder id(UUID id) {
      car.id = id;
      return this;
    }
    
    public Car build() {
      return car;
    }
  }
}
