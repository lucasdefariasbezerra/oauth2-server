package com.auth.tokenserver.repository;

import com.auth.tokenserver.model.CustomUser;
import com.auth.tokenserver.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Integer> {

    Optional<CustomUser> findFirstByUsername(String username);

    @Query("SELECT c from CustomUser c")
    List<CustomUser> getAllUsers();
}
