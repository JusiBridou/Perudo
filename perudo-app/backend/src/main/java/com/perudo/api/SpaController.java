package com.perudo.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

    // Simplified: Only handle root explicitly
    // All other SPA routing is handled by StaticResourceConfig
    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }
}


