package com.apirest.project.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateUserDTO {

    @NotNull(message = "Debe ingresar un ID para actualizar al usuario")
    private Long id;
    @NotBlank(message = "Debe ingresar un nombre de usuario valido")
    private String username;
    @NotBlank(message = "Debe ingresar una contraseña valida")
    private String password;

    public UpdateUserDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
