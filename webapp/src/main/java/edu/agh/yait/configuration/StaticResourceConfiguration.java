package edu.agh.yait.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Norbert Skurnóg on 6/5/17.
 */
@Configuration
@EnableWebMvc
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {

    private static final String STATIC_RESOURCES_LOCATION = "classpath:/static/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(STATIC_RESOURCES_LOCATION);
        registry.addResourceHandler("/build/**").addResourceLocations(STATIC_RESOURCES_LOCATION + "build/");
        registry.addResourceHandler("/node_modules/**").addResourceLocations(STATIC_RESOURCES_LOCATION + "node_modules/");
        registry.addResourceHandler("/js/**").addResourceLocations(STATIC_RESOURCES_LOCATION + "js/");
        registry.addResourceHandler("/index.html").addResourceLocations(STATIC_RESOURCES_LOCATION + "index.html");
    }
}