package com.nafisulbari.eib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import static java.util.Collections.singletonList;


/**
 * EibApplication runs the SpringBootApplication
 *
 *
 * @author  Ahmed Nafisul Bari
 */


@SpringBootApplication
public class EibApplication {

    //Configuring Freemarker to use JspTagLibs
    public EibApplication(FreeMarkerConfigurer freeMarkerConfigurer) {
        freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(singletonList("/META-INF/security.tld"));
    }


    public static void main(String[] args) {
        SpringApplication.run(EibApplication.class, args);


    }





}
