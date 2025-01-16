package dev.tanvx.customer_service;

import dev.tanvx.common_library.config.SharedConfigurationReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SharedConfigurationReference.class)
public class BusinessServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BusinessServiceApplication.class, args);
  }
}
