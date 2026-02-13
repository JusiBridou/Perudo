package com.perudo.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/debug")
public class DebugAssetsController {

    @GetMapping("/assets")
    public Map<String, Object> listAssets() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:/public/assets/*");

        List<String> names = new ArrayList<>();
        for (Resource resource : resources) {
            if (resource != null && resource.exists()) {
                names.add(resource.getFilename());
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("count", names.size());
        result.put("assets", names);
        return result;
    }
}
