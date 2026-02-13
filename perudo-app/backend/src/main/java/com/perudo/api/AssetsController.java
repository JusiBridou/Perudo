package com.perudo.api;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class AssetsController {

    // Serve assets from both /assets/* AND /app-assets/* paths
    // This is a workaround for Railway Edge blocking /assets/*
    @GetMapping({"/assets/{filename:.+}", "/app-assets/{filename:.+}"})
    public ResponseEntity<Resource> serveAsset(@PathVariable String filename) throws IOException {
        Resource resource = new ClassPathResource("public/assets/" + filename);
        
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        
        // Set proper content type
        String contentType = "application/octet-stream";
        if (filename.endsWith(".js")) {
            contentType = "application/javascript";
        } else if (filename.endsWith(".css")) {
            contentType = "text/css";
        }
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=3600")
                .body(resource);
    }
}
