package dev.tanvx.job;

import dev.tanvx.common_library.config.SharedConfigurationReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@EnableFeignClients
@SpringBootApplication
@Import(SharedConfigurationReference.class)
public class JobServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(JobServiceApplication.class, args);
  }
}
