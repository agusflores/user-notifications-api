package com.apirest.project.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenido;
    @ManyToMany(mappedBy = "notificaciones")
    public List<Usuario> usuarios;

    public Notificacion() {
    }

    public Notificacion(String contenido) {
        this.contenido = contenido;
    }

    public Notificacion(String contenido, List<Usuario> usuarios) {
        this.contenido = contenido;
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                ", usuarios=" + usuarios +
                '}';
    }
}
