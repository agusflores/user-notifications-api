package com.apirest.project.dtos;

import com.apirest.project.entities.Notificacion;

import java.util.List;

public class ViewNotificationDTO {
    private Long id;
    private String contenido;
    private List<ViewListUsersDTO> listUsers;

    public ViewNotificationDTO(Notificacion notificacion, List<ViewListUsersDTO> listUsersDTO) {
        this.id = notificacion.getId();
        this.contenido = notificacion.getContenido();
        this.listUsers = listUsersDTO;
    }

    public ViewNotificationDTO(Long id, String contenido, List<ViewListUsersDTO> listUsers) {
        this.id = id;
        this.contenido = contenido;
        this.listUsers = listUsers;
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

    public List<ViewListUsersDTO> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<ViewListUsersDTO> listUsers) {
        this.listUsers = listUsers;
    }
}
