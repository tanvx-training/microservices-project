package dev.tanvx.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Job Service Application
 * 
 * This service is responsible for managing job-related operations in the recruitment system.
 * It provides functionality for:
 * - Creating and managing job postings
 * - Job search and filtering
 * - Job application tracking
 * - Job status management
 * 
 * Key features:
 * - RESTful API endpoints for job operations
 * - Integration with other services via Feign clients
 * - Database persistence with JPA
 * - Service discovery with Eureka
 * - Circuit breaker implementation
 * - Distributed tracing
 * 
 * @author tanvx
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableJpaAuditing
public class JobServiceApplication {

    /**
     * Main method to start the Job Service application
     * 
     * @param args Command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(JobServiceApplication.class, args);
    }
} 