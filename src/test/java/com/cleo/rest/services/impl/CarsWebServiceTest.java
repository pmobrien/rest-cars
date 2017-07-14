package com.cleo.rest.services.impl;

import com.cleo.rest.exceptions.InvalidIdException;
import com.cleo.rest.pojo.Car;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CarsWebServiceTest {

  private CarsWebService service;
  
  @BeforeTest
  protected void beforeTest() {

    service = Mockito.mock(CarsWebService.class);
  }

  @Test
  public void testValidateId() {
    Mockito.doCallRealMethod().when(service).validateId(Matchers.anyString());
    Mockito.doReturn(new Car()).when(service).findCar(Matchers.anyString());
    
    try {
      service.validateId("2b47e961-4175-4423-9b36-c67526b432f8");
    } catch(InvalidIdException ex) {
      Assert.fail("failed", ex);
    }
    
    Mockito.verify(service).validateId(Matchers.anyString());
  }

  @Test(dataProvider = "testValidateIdDataProvider", expectedExceptions = InvalidIdException.class)
  public void testValidateId(String value) {
    Mockito.doCallRealMethod().when(service).validateId(Matchers.anyString());
    Mockito.doReturn(null).when(service).findCar(Matchers.anyString());
    
    service.validateId(null);
  }
  
  @DataProvider
  protected Object[][] testValidateIdDataProvider() {
    return new Object[][] {
      { null },
      { "" },
      { "x" },
      { "xxxx" },
      { "2b47e961-4175-4423-9b36-c67526b432f" },  // missing a character
      { "2b47e961-4175-4423-9b36-c67526b432f8" }  // good id, but doesn't exist
    };
  }


  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Test
  public void testAddCar() {
    try{
      service.addCar(null);
    } catch(Exception ex) {
      Assert.fail("shouldn't add null car", ex);
    }
  }
}
