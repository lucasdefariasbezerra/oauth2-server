package com.auth.tokenserver.api;

import com.auth.tokenserver.model.UserDTO;
import com.auth.tokenserver.service.CustomAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    @Autowired
    CustomAuthService authService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String saveUser(@RequestBody UserDTO user) {
        return String.valueOf(authService.saveUser(user));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> get() {
        return authService.allUsers();
    }

    @GetMapping(value = "/current-user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> currentUser(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(authService.findUserByName(user.getUsername()));
    }
}
