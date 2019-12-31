package com.auth.tokenserver.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TokenApi {

    @GetMapping()
    public String testApi() {
        return "testing scurity";
    }
}
