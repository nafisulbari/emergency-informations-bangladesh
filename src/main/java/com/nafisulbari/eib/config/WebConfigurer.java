package com.nafisulbari.eib.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 *
 * @author  Ahmed Nafisul Bari
 *
 */


@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfigurer implements WebMvcConfigurer {

    //Setting citizen-records folder as a configuration----------------------------------------------------------------
    public static final String recordsUploadDirectory = System.getProperty("user.dir") + "\\citizen-records";

    //Manually adding resources path and its folders------------------------------------------------------------------
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/citizen-records/**").addResourceLocations("file:" + recordsUploadDirectory + "\\");
        registry.addResourceHandler("/static/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
}
