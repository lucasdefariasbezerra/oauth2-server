package com.auth.tokenserver.api;

import com.auth.tokenserver.model.CustomUserDTO;
import com.auth.tokenserver.service.CustomAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    CustomAuthService authService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String saveUser(@RequestBody CustomUserDTO user) {
        return String.valueOf(authService.saveUser(user));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllUsers() {
        return authService.getAllUsers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllUsersById(@PathVariable final Integer id) {
        return authService.getUserById(id);
    }
}
