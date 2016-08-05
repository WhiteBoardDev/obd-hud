package com.wbdev.obdhud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class ObdHudApplicationConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SPRING_WEB)
                .groupName("ODBHUD")
                .apiInfo(new ApiInfoBuilder()
                        .title("OBD HUD REST API")
                        .build())
                .select().paths(not(or(PathSelectors.regex("/error"),
                        PathSelectors.regex("/manage/.*"),
                        PathSelectors.regex("/env/.*"))))
                .build();
    }



    @Bean
    public Lock communcationDeviceLock(){
        return new ReentrantLock();
    }

}
