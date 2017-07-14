package com.cleo.rest.services.impl;

import com.cleo.rest.pojo.Car;
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

    // THROWS ERROR ->  Mockito.mock(Car.class);
    car = new Car();
  }


  @Test(dataProvider = "testToStringDataProvider")
  public void testToString(Car someCar, String expected) {

    Assert.assertEquals(someCar.toString(), expected);
  }

  @Test(dataProvider = "testUpdateCarDataProvider")
  public void testUpdateCar(Car someCar, boolean expectedResult) {

    Assert.assertEquals(car.equals(someCar), expectedResult);
  }


  @DataProvider(name = "testUpdateCarDataProvider")
  protected Object[][] testUpdateCarDataProvider() {
    Car car1 = new Car(); // make this same as car
    Car car2 = new Car(); // make this different than car
    Car car3 = new Car(); // make this same as car

    car1 = car1.updateCar(car1, car);

    car2 = car2.updateCar(car2, car);
    car2.setYear(2020);

    car3 = car3.updateCar(car3, car);

    return new Object[][] {
        { car1, true },
        { car2, false },
        { car3, true }
    };
  }

  @DataProvider(name = "testToStringDataProvider")
  protected  Object[][] testToStringDataProvider() {
    car.setColor("White");
    car.setMake("Buick");
    car.setModel("Encore");

    // List out several car examples:
    Car car1 = new Car();
    Car car2 = new Car();
    Car car3 = new Car();

    car1 = car1.updateCar(car1, car);
    car1.setMake("Ford");
    car1.setYear(1969);

    car2 = car2.updateCar(car2, car);
    car2.setYear(2020);
    car2.setMake("Chevy");

    car3 = car3.updateCar(car3, car);
    car3.setYear(1994);
    car3.setMake("VW");

    return new Object[][] {
        { car1, "1969 White Ford Encore" },
        { car2, "2020 White Chevy Encore" },
        { car3, "1994 White VW Encore"}
    };
  }
}
