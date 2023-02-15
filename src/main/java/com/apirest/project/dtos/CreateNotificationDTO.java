package com.apirest.project.dtos;


import jakarta.validation.constraints.NotBlank;

public class CreateNotificationDTO {
    @NotBlank(message = "Debe ingresar un contenido de notificacion valido")
    private String contenido;

    public CreateNotificationDTO(String contenido) {
        this.contenido = contenido;
    }

    public CreateNotificationDTO() {
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
