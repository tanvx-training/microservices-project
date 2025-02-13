package dev.tanvx.customer_service.config;

import static java.util.concurrent.TimeUnit.SECONDS;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

  @Bean
  public Retryer retryer() {
    return new Retryer.Default(1000, SECONDS.toMillis(1), 3);
  }

  @Bean
  public Request.Options timeoutConfiguration() {
    return new Request.Options(5, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, true);
  }

  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }
}
