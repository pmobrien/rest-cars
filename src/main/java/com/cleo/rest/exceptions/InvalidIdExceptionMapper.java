package com.cleo.rest.exceptions;

import javax.print.attribute.standard.Media;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * Created by agesmer on 7/10/17.
 */
@Provider
public class InvalidIdExceptionMapper implements ExceptionMapper<Throwable> {

  /**
   *
   * @param t The invalid id exception.
   *
   * @return The InvalidIdException as JSON
   */
  @Override
  public Response toResponse(Throwable t) {
    // Print out null id exception to stack
    t.printStackTrace(System.out);

    try {
      throw t;

    } catch (InvalidIdException ex) {
        return Response.status(((WebApplicationException)ex).getResponse().getStatus())
            .entity(new ErrorMessage(ex.getMessage(), ex.getResponse().getStatus()))
            .type(MediaType.APPLICATION_JSON)
            .build();
    }

    catch (Throwable ex) {
      return Response.serverError()
          .entity(new ErrorMessage("Houston, we have a problem", 404))
          .type(MediaType.APPLICATION_JSON)
          .build();
    }
  }



  private static class ErrorMessage {

    private String message;
    private int status;

    public ErrorMessage(String message, int status) {
      this.message = message;
      this.status = status;
    }

    public String getMessage() {
      return this.message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public int getStatus() {
      return this.status;
    }

    public void setStatus(int status) {
      this.status = status;
    }
  }
}





