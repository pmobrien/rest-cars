package com.cleo.rest;

import com.cleo.rest.services.impl.CarsWebService;
import com.cleo.rest.services.impl.HelloWorldWebService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Application {
  
  public static void main(String[] args) throws Exception {
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/api");

    Server server = new Server(8080);
    server.setHandler(context);

    ServletHolder servlet = context.addServlet(ServletContainer.class, "/*");
    servlet.setInitOrder(0);
    
    servlet.setInitParameter(
        "jersey.config.server.provider.classnames",
        String.join(
            ";",
            HelloWorldWebService.class.getCanonicalName(),
            CarsWebService.class.getCanonicalName()
        )
    );

    try {
      server.start();
      server.join();
    } finally {
      server.destroy();
    }
  }
}
