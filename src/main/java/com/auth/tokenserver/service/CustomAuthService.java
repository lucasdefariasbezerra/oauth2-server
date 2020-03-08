package com.auth.tokenserver.service;

import com.auth.tokenserver.model.CustomUser;
import com.auth.tokenserver.model.UserDTO;
import com.auth.tokenserver.payloadManager.PayloadHandler;
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

/* testing web hoodssk */
@Service
public class CustomAuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PayloadHandler<UserDTO, CustomUser> payloadHandler;

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
                .orElseThrow(() -> new UsernameNotFoundException("User was not found"));
        Collection<GrantedAuthority> list = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        list.add(authority);
        return new User(customUser.getUsername(), customUser.getPassword(), list);
    }

    @Transactional
    public int saveUser (UserDTO userDTO) {
        CustomUser model = new CustomUser();
        String password = PasswordHelper.getEncryptedPassword(userDTO.getPassword());
        model.setUsername(userDTO.getUsername());
        model.setPassword(password);
        return userRepository.save(model).getId();
    }

    public ResponseEntity<?> allUsers() {
        List<CustomUser> list = userRepository.getAllUsers();
        payloadHandler.mapToDTO(list);
        return payloadHandler.buildPayload(HttpStatus.OK, true);
    }
}
