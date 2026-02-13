package com.perudo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {
    
    private static final Logger logger = LoggerFactory.getLogger(StaticResourceConfig.class);
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("Configuring static resource handlers");
        
        // Mapper /assets/** avec la PLUS HAUTE priorité
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/public/assets/")
                .setCachePeriod(3600)
                .resourceChain(false);
        
        logger.info("Registered handler for /assets/** -> classpath:/public/assets/");
        
        // Mapper les autres fichiers statiques à la racine (index.html, favicon, etc)
        registry.addResourceHandler("/*.html", "/*.ico", "/*.png", "/*.jpg", "/*.css", "/*.js")
                .addResourceLocations("classpath:/public/")
                .setCachePeriod(3600)
                .resourceChain(false);
        
        logger.info("Registered handler for static files at root -> classpath:/public/");
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward root to index.html
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        logger.info("Configured view controller for / -> forward:/index.html");
    }
}
