package com.auth.tokenserver.repository;

import com.auth.tokenserver.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Integer> {

    Optional<CustomUser> findFirstByUsername(String username);

    @Query("SELECT new com.auth.tokenserver.model.CustomUser(c.id,c.username) from CustomUser c")
    List<CustomUser> getAllUsers();
}
