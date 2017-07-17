package com.cleo.rest.services.impl;

import com.cleo.rest.pojo.Car;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by agesmer on 7/13/17.
 */
public class CarTest {

  private Car car;

  @BeforeTest
  protected void beforeTest() {
    car = Mockito.mock(Car.class);
  }

  @Test(dataProvider = "testUpdateCarDataProvider")
  public void testUpdateCar(Car newCar, boolean expected) {

    Car car = new Car.Builder()
                  .id(UUID.randomUUID())
                  .make("Buick")
                  .model("Encore")
                  .color("White")
                  .year(2017)
                  .build();

    car = car.updateCar(newCar);
    Assert.assertEquals(car.equals(newCar), expected);
  }

  @DataProvider(name = "testUpdateCarDataProvider")
  protected Object[][] testUpdateCarDataProvider() {

    // SHOULD ALWAYS BE TRUE, DESIRED OUTPUT IS TO ENSURE THAT THE CAR UPDATES PROPERLY
    return new Object[][] {
        { new Car.Builder().id(UUID.randomUUID()).make("Buick").model("Envision").color("White").year(2017).build(), true },
        { new Car.Builder().id(UUID.randomUUID()).make("Honda").model("CRV").color("Grey").year(2014).build(), true },
        { new Car.Builder().id(UUID.randomUUID()).make("Lamborghini").model("One Off").color("Black").year(2016).build(), true }
    };
  }


  @Test(dataProvider = "testToStringDataProvider")
  public void testToString(Car someCar, String expected) {

    Assert.assertEquals(someCar.toString(), expected);
  }

  @DataProvider(name = "testToStringDataProvider")
  protected  Object[][] testToStringDataProvider() {
    return new Object[][] {
        { new Car.Builder().id(UUID.randomUUID()).make("Buick").model("Envision").color("White").year(2017).build(),
            "2017 White Buick Envision" },

        { new Car.Builder().id(UUID.randomUUID()).make("Honda").model("CRV").color("Grey").year(2014).build(),
            "2014 Grey Honda CRV" },

        { new Car.Builder().id(UUID.randomUUID()).make("Lamborghini").model("One Off").color("Black").year(2016).build(),
            "2016 Black Lamborghini One Off" }
    };
  }
}
