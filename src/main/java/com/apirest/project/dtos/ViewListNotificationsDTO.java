package com.apirest.project.dtos;

import com.apirest.project.entities.Notificacion;

import java.util.List;

public class ViewListNotificationsDTO {

    private Long id;
    private String contenido;

    public ViewListNotificationsDTO(Notificacion notificacion) {
        this.id = notificacion.getId();
        this.contenido = notificacion.getContenido();
    }

    public ViewListNotificationsDTO(Long id, String contenido) {
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
