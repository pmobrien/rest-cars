package com.cleo.rest.services.impl;

import com.cleo.rest.pojo.Car;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by agesmer on 7/13/17.
 */
public class CarTest {

  private Car car;

  @BeforeTest
  protected void beforeTest() {

    // THROWS ERROR --> car = Mockito.mock(Car.class);
    car = new Car();
  }

  @Test(dataProvider = "testUpdateCarDataProvider")
  public void testUpdateCar(Car newCar, boolean expected) {

    Car car = new Car.Builder()
                  .make("Buick")
                  .model("Encore")
                  .color("White")
                  .year(2017)
                  .build();

    Assert.assertEquals(car.updateCar(newCar).equals(newCar), expected);
  }


  @DataProvider(name = "testUpdateCarDataProvider")
  protected Object[][] testUpdateCarDataProvider() {

    return new Object[][] {
        { new Car.Builder().make("Buick").model("Encore").color("White").year(2017), true },
        { new Car.Builder().make("Buick").model("Encore").color("White").year(2017), true },
        { new Car.Builder().make("Buick").model("Encore").color("White").year(2017), true }
    };
  }
















    /*
  @Test(dataProvider = "testToStringDataProvider")
  public void testToString(Car someCar, String expected) {

    Assert.assertEquals(someCar.toString(), expected);
  }*/

  /*
  @DataProvider(name = "testToStringDataProvider")
  protected  Object[][] testToStringDataProvider() {
    car.setColor("White");
    car.setMake("Buick");
    car.setModel("Encore");

    // List out several car examples:
    Car car1 = new Car();
    Car car2 = new Car();
    Car car3 = new Car();

    car1 = car1.updateCar(car);
    car1.setMake("Ford");
    car1.setYear(1969);

    car2 = car2.updateCar(car);
    car2.setYear(2020);
    car2.setMake("Chevy");

    car3 = car3.updateCar(car);
    car3.setYear(1994);
    car3.setMake("VW");

    return new Object[][] {
        { car1, "1969 White Ford Encore" },
        { car2, "2020 White Chevy Encore" },
        { car3, "1994 White VW Encore"}
    };
  }
  */

}
