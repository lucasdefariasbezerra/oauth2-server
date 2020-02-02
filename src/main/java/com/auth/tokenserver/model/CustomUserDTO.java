package com.auth.tokenserver.model;

import java.util.Objects;

public class CustomUserDTO {
    private Integer id;
    private String username;
    private String password;

    public CustomUserDTO() {
    }

    public CustomUserDTO(Integer id, String username) {
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
        CustomUserDTO customUserDTO = (CustomUserDTO) o;
        return Objects.equals(id, customUserDTO.id) &&
                Objects.equals(username, customUserDTO.username) &&
                Objects.equals(password, customUserDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
