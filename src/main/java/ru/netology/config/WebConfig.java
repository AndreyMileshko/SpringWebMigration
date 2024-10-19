package ru.netology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        final var adapter = new RequestMappingHandlerAdapter();
        adapter.getMessageConverters().add(new GsonHttpMessageConverter());
        return adapter;
    }
}