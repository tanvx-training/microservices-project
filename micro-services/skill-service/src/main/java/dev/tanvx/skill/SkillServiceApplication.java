package dev.tanvx.skill;

import dev.tanvx.common_library.config.SharedConfigurationReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@EnableFeignClients
@SpringBootApplication
@Import(SharedConfigurationReference.class)
public class SkillServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SkillServiceApplication.class, args);
  }
}
