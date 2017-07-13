package com.cleo.rest.services.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by agesmer on 7/12/17.
 */
public class Tester {

  @Test
  public void passingInvalidIdShouldReturnError() {

    CarsWebService tester = new CarsWebService();

    assertEquals("blah blah blah", tester.getCarById("abcd"));


  }





}
