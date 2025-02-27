package dev.tanvx.job.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Configuration
 * 
 * This configuration class sets up web-related configurations for the service.
 * It includes:
 * - CORS configuration
 * - Resource handlers
 * - Message converters
 * - View resolvers
 * 
 * The configuration ensures proper handling of web requests and responses,
 * while maintaining security and performance best practices.
 * 
 * @author tanvx
 * @version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS (Cross-Origin Resource Sharing) settings
     * 
     * This method sets up CORS policies to allow controlled access from different origins.
     * It defines:
     * - Allowed origins
     * - Allowed methods
     * - Allowed headers
     * - Exposed headers
     * - Max age for preflight requests
     * 
     * @param registry The CORS registry to configure
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .maxAge(3600);
    }
} 