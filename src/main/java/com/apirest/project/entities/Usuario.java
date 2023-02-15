package com.apirest.project.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @ManyToMany
    @JoinTable(name = "user_notificacion",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "notificacion_id", referencedColumnName = "id"))
    public List<Notificacion> notificaciones;

    public Usuario() {
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario(String username, String password, List<Notificacion> notificaciones) {
        this.username = username;
        this.password = password;
        this.notificaciones = notificaciones;
    }

    public Long getId() {
        return id;
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

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public void addNotification(Notificacion notificacion) {
        if (this.notificaciones.size() < 3) {
            notificaciones.add(notificacion);
        } else {
            System.err.println("ERROR!!!!");
        }
    }

    public void deleteNotification(Notificacion notificacion) {
        if (!this.notificaciones.isEmpty()) {
            notificaciones.remove(notificacion);
        } else {
            System.err.println("ERROR!!!!");
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", notificaciones=" + notificaciones +
                '}';
    }
}
