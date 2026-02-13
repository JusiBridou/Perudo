package com.perudo.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {

    @RequestMapping({
            "/",
            "/{path:[^\\.]*}",
            "/{path:[^\\.]*}/**"
    })
    public String index() {
        // Let Spring serve index.html as a static resource.
        return "forward:/index.html";
    }
}


