package com.cleo.rest.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by agesmer on 7/10/17.
 */
public class InvalidIdException extends WebApplicationException {

  public InvalidIdException(String message, Response.Status status) {
    super(message, status);
  }
}