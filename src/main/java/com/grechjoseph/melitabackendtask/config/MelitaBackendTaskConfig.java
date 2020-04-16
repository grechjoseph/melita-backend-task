package com.grechjoseph.melitabackendtask.config;

import com.grechjoseph.melitabackendtask.client.melita.client.MelitaCustomerClient;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableCaching
@EnableSwagger2
@EnableFeignClients(clients = MelitaCustomerClient.class)
public class MelitaBackendTaskConfig {

    /**
     * Customization configuration for Swagger2.
     * @return Docket to allow Selector Builder to control the endpoints exposed by Swagger.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .alternateTypeRules()
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.grechjoseph.melitabackendtask"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Melita Backend Task")
                .description("Adding and retrieving Customer details from a Melita API.")
                .build();
    }

}
