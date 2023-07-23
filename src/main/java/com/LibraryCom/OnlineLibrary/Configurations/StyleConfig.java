package com.LibraryCom.OnlineLibrary.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//Spring MVC configurer
@Configuration
@EnableWebMvc
public class StyleConfig implements WebMvcConfigurer {
    /*
        Adding the resource handler to let see the spring our css's and js's files
    */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
