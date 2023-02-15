package com.apirest.project.dtos;

import jakarta.validation.constraints.NotBlank;

public class CreateUserDTO {
    @NotBlank(message = "Debe ingresar un nombre de usuario valido")
    private String username;
    @NotBlank(message = "Debe ingresar una contraseña valida")
    private String password;

    public CreateUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
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
