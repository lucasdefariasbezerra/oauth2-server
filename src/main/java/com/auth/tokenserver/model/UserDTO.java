package com.auth.tokenserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class UserDTO {
    private Integer id;
    private String username;
    private String password;

    public UserDTO() {
    }

    public UserDTO(CustomUser customUser) {
        this.id = customUser.getId();
        this.username = customUser.getUsername();
    }

    public UserDTO(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) &&
                Objects.equals(username, userDTO.username) &&
                Objects.equals(password, userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
