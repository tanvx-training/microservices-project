package dev.tanvx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * API Gateway Application
 * 
 * Đây là entry point của API Gateway service, cung cấp các chức năng:
 * - Service Discovery với Eureka
 * - Dynamic Routing
 * - Load Balancing
 * - Circuit Breaking
 * - Rate Limiting
 * - Authentication & Authorization
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
} 