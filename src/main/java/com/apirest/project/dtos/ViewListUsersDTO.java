package com.apirest.project.dtos;

import com.apirest.project.entities.Usuario;

import java.util.List;

public class ViewListUsersDTO {

    private Long id;
    private String username;

    public ViewListUsersDTO(Usuario user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
