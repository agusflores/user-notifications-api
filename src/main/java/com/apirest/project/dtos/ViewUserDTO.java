package com.apirest.project.dtos;

import com.apirest.project.entities.Usuario;

import java.util.List;

public class ViewUserDTO {

    private Long id;
    private String username;
    private String password;
    private List<ViewListNotificationsDTO> listNotifications;

    public ViewUserDTO(Usuario user, List<ViewListNotificationsDTO> listNotificationsDTOS) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.listNotifications = listNotificationsDTOS;
    }

    public ViewUserDTO(Long id, String username, String password, List<ViewListNotificationsDTO> listNotifications) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.listNotifications = listNotifications;
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

    public List<ViewListNotificationsDTO> getListNotifications() {
        return listNotifications;
    }

    public void setListNotifications(List<ViewListNotificationsDTO> listNotifications) {
        this.listNotifications = listNotifications;
    }
}
