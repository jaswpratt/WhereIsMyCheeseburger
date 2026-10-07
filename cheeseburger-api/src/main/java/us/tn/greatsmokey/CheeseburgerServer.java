package us.tn.greatsmokey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Entry point for the WhereIsMyCheeseburger backend.
 * <p>
 * Bootstraps the Spring Boot application context, auto-configuring
 * component scanning, JPA/Hibernate, and the embedded web server. This
 * class also extends {@link SpringBootServletInitializer} so the
 * application can be packaged as a WAR and deployed to an external
 * servlet container (Apache Tomcat), in addition to running standalone
 * via {@code mvn spring-boot:run}.
 */
@SpringBootApplication
public class CheeseburgerServer extends SpringBootServletInitializer {

   /**
    * Launches the application as a standalone, embedded-server process.
    *
    * @param args command-line arguments passed through to Spring Boot
    */
   public static void main(String[] args) {
      SpringApplication.run(CheeseburgerServer.class, args);
   }

   /**
    * Configures the application when deployed as a WAR to an external
    * servlet container, rather than run via {@link #main(String[])}.
    *
    * @param builder the builder used to configure the Spring application
    * @return the builder, configured with this class as its source
    */
   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      return builder.sources(CheeseburgerServer.class);
   }
}