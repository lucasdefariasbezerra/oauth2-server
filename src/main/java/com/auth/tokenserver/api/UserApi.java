package com.auth.tokenserver.api;

import com.auth.tokenserver.model.CustomUser;
import com.auth.tokenserver.model.CustomUserDTO;
import com.auth.tokenserver.payloadManager.GenericPayloadGenerator;
import com.auth.tokenserver.service.CustomAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    CustomAuthService authService;

    @Autowired
    GenericPayloadGenerator<CustomUserDTO, CustomUser> userPayloadGenerator;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody CustomUserDTO user) {
        return authService.saveUser(user);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllUsers() {
        userPayloadGenerator.mapToDTO(authService.getAllUsers());
        return userPayloadGenerator.buildDataResponsePayload(HttpStatus.OK, true);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllUsersById(@PathVariable final Integer id) {
        CustomUser user = authService.getUserById(id);
        if (user != null) {
            userPayloadGenerator.mapToDTO(user);
            return userPayloadGenerator.buildDataResponsePayload(HttpStatus.OK, false);
        }
        return userPayloadGenerator.buildMessageResponsePayload(HttpStatus.NOT_FOUND, "error", "user was not found");
    }
}
