package pl.hodan.vehicles.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SpringDocConfig {

    @Bean
    public OpenAPI baseOpenAPi(){
        return new OpenAPI().info(new Info().title("Vehicles Doc").version("1.0.0").description("Vehicles Doc"));

    }
}
