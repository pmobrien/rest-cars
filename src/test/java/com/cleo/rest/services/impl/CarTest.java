package com.cleo.rest.services.impl;

import com.cleo.rest.pojo.Car;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by agesmer on 7/13/17.
 */
public class CarTest {

  private Car car;

  @BeforeTest
  protected void beforeTest() {
    Assert.fail("crap");
    car = Mockito.mock(Car.class);
  }


  @Test
  public void testToString() {
    //Assert.fail();
    car.toString();
  }

  @Test
  public void testUpdateCar() {
    Car someCar = new Car();

    car.updateCar(car, someCar);
  }


  @Test
  public void testPatchCar() {
    Car someCar = new Car();

    car.updateCar(car, someCar);
  }

}
