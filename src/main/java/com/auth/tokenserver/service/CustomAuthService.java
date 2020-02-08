package com.auth.tokenserver.service;

import com.auth.tokenserver.model.CustomUser;
import com.auth.tokenserver.model.CustomUserDTO;
import com.auth.tokenserver.payloadManager.GenericPayloadGenerator;
import com.auth.tokenserver.repository.UserRepository;
import com.auth.tokenserver.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomAuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GenericPayloadGenerator<CustomUserDTO, CustomUser> userPayloadGenerator;

    @PostConstruct
    private void setUpDefaultUser() {
        CustomUser model = new CustomUser();
        String password = PasswordHelper.getEncryptedPassword("password");

        model.setId(1);
        model.setUsername("admin");
        model.setPassword(password);
        userRepository.save(model);
    }


    @Override
    //@Cacheable(value = "user",key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = userRepository
                .findFirstByUsername(username)
                .orElseThrow(() -> {
                    throw  new UsernameNotFoundException("User was not found");
                });
        Collection<GrantedAuthority> list = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        list.add(authority);
        return new User(customUser.getUsername(), customUser.getPassword(), list);
    }

    @Transactional
    public ResponseEntity<?> saveUser(CustomUserDTO customUserDTO) {
        CustomUser model = new CustomUser();
        String password = PasswordHelper.getEncryptedPassword(customUserDTO.getPassword());
        model.setUsername(customUserDTO.getUsername());
        model.setPassword(password);
        if (userRepository.save(model).getId() == null) {
            return userPayloadGenerator.buildMessageResponsePayload(HttpStatus.OK, "desc", "user saved");
        }
        return userPayloadGenerator.buildMessageResponsePayload(HttpStatus.BAD_REQUEST, "error", "user not saved");
    }

    public ResponseEntity<?> getAllUsers() {
        List<CustomUser> list = userRepository.getAllUsers();
        userPayloadGenerator.mapToDTO(list);
        return userPayloadGenerator.buildDataResponsePayload(HttpStatus.OK, true);
    }

    public ResponseEntity<?> getUserById(int id) {
        CustomUser user = userRepository
                .findById(id)
                .orElse(null);
        if (user == null) {
            String errorMessage = "user was not found";
            return userPayloadGenerator.buildMessageResponsePayload(HttpStatus.NOT_FOUND, "error", errorMessage);
        }
        userPayloadGenerator.mapToDTO(user);
        return userPayloadGenerator.buildDataResponsePayload(HttpStatus.OK, false);
    }
}
