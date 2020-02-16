package com.nafisulbari.eib.Storage;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfigurer implements WebMvcConfigurer {


    public static String imageUploadDirectory = System.getProperty("user.dir") + "\\citizen-images";
    public static String qrUploadDirectory = System.getProperty("user.dir") + "\\citizen-qr";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/citizen-images/**").addResourceLocations("file:" + imageUploadDirectory + "\\");
        registry.addResourceHandler("/citizen-qr/**").addResourceLocations("file:" + qrUploadDirectory + "\\");
    }
}
