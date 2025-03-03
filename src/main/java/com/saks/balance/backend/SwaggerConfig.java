package com.saks.balance.backend;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
            .components(new Components())
            .info(apiInfo());
    }

    private Info apiInfo(){
        return new Info()
            .title("BalanceGame API Swagger")
            .description("밸런스게임 API Swagger 테스트 페이지")
            .version("0.0.1");
    }
}
