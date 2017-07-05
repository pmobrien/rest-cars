package com.cleo.rest;

import com.cleo.rest.services.impl.CarsWebService;
import com.cleo.rest.services.impl.HelloWorldWebService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class Application {
  
  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);
    ServletContextHandler context = new ServletContextHandler(server, "/api");
    
    context.addServlet(
        new ServletHolder(
            new ServletContainer(
                new ResourceConfig()
                    .register(HelloWorldWebService.class)
                    .register(CarsWebService.class)
            )
        ),
        "/*"
    );

    try {
      server.start();
      server.join();
    } catch(Exception ex) {
      ex.printStackTrace(System.out);
    } finally {
      server.destroy();
    }
  }
}
