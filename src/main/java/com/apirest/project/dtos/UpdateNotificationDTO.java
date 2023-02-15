package com.apirest.project.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateNotificationDTO {
    @NotNull(message = "Debe ingresar un ID para actualizar la notificacion")
    private Long id;

    @NotBlank(message = "Debe ingresar un contenido de notificacion valido")
    private String contenido;

    public UpdateNotificationDTO(Long id, String contenido) {
        this.id = id;
        this.contenido = contenido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
