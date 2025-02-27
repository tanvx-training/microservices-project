package dev.tanvx.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server Application
 * 
 * This is the main class for the Eureka Server application which provides service discovery
 * and registration for the microservices' architecture. It enables other services to:
 * - Register themselves with the Eureka Server
 * - Discover other services registered with Eureka
 * - Update their status through heartbeats
 * 
 * Key features:
 * - Service Registration
 * - Service Discovery
 * - Load Balancing support
 * - Self-preservation mode
 * - Health monitoring
 * 
 * @author tanvx
 * @version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    /**
     * Main method to start the Eureka Server application
     * 
     * @param args Command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
