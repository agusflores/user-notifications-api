package com.apirest.project.service;

import com.apirest.project.dtos.CreateUserDTO;
import com.apirest.project.dtos.UpdateUserDTO;
import com.apirest.project.dtos.ViewUserDTO;
import com.apirest.project.entities.Notificacion;
import com.apirest.project.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public void createUser(CreateUserDTO userDTO);

    public void updateUser(UpdateUserDTO userDTO);

    public List<Usuario> getAll();

    public Usuario getById(Long id);

    public Boolean repeatedUsername(String nombreBuscado);

    public Usuario getByUsername(String username);

    public List<Usuario> getUsersWithoutNotification();

    public Usuario deleteById(Long id);

    public void deleteAll();

    public List<Notificacion> assignUser(Usuario usuario, List<Integer> listNotificationsId);

    public List<Notificacion> unassignUser(Usuario usuario, List<Integer> listNotificationsId);
}
