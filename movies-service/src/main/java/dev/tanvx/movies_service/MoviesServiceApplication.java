package dev.tanvx.movies_service;

import dev.tanvx.common_library.config.SharedConfigurationReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SharedConfigurationReference.class)
public class MoviesServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoviesServiceApplication.class, args);
  }
}
