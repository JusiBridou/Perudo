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
        
        // CRITICAL: Assets MUST be served with highest priority
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/public/assets/")
                .setCachePeriod(3600)
                .resourceChain(false);
        
        logger.info("Registered handler for /assets/** -> classpath:/public/assets/");
        
        // Serve all static files from /public/
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/public/")
                .setCachePeriod(3600)
                .resourceChain(false);
        
        logger.info("Registered handler for /** -> classpath:/public/");
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // SPA fallback: any non-existent route goes to index.html
        // But this has LOWER priority than ResourceHandlers
        registry.addViewController("/{spring:[^\\.]*}").setViewName("forward:/index.html");
        registry.setOrder(Ordered.LOWEST_PRECEDENCE);
        logger.info("Configured SPA fallback controller");
    }
}
