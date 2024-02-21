package com.example.transport_layer.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Open Api Configuration
 * @author Vladimir Krasnov
 */
@OpenAPIDefinition(
        info = @Info(
                description = "Transport layer"
        )
)
@Configuration
public class OpenApiConfig {

        @Bean
        public RestTemplate restTemplate() {
                return new RestTemplate();
        }
}
