package com.company.Configuration;

import com.company.Controller.Controller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfigurationFactory {

    @Bean
    public Controller controller(){
        return new Controller();
    }
}
