package com.apirest.project.service;

import com.apirest.project.dtos.CreateUserDTO;
import com.apirest.project.dtos.UpdateUserDTO;
import com.apirest.project.entities.Notificacion;
import com.apirest.project.entities.Usuario;
import com.apirest.project.helper.ApiHelper;
import com.apirest.project.repository.NotificationRepository;
import com.apirest.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private NotificationRepository notificacionRepository;

    @Override
    public void createUser(CreateUserDTO userDTO) {
        Usuario usuario = ApiHelper.createUserToEntity(userDTO);
        repository.save(usuario);
    }

    @Override
    public void updateUser(UpdateUserDTO userDTO) {
        Optional<Usuario> optUser = repository.findById(userDTO.getId());
        if (optUser.isPresent()) {
            Usuario user = ApiHelper.updateUserFunction(optUser.get(), userDTO);
            repository.save(user);
        }
    }

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    @Override
    public Usuario getById(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.orElse(null);
    }

    @Override
    public Boolean repeatedUsername(String nombreBuscado) {
        List<Usuario> usuarios = getAll();
        Optional<Usuario> repeatedUser =
                usuarios.stream().filter(u ->
                        u.getUsername().equalsIgnoreCase(nombreBuscado)).findFirst();
        if(repeatedUser.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Usuario getByUsername(String username) {
        List<Usuario> usuarios = getAll();
        Optional<Usuario> userToFind =
                usuarios.stream().filter(u ->
                        u.getUsername().equalsIgnoreCase(username)).findFirst();
        return userToFind.orElse(null);
    }

    @Override
    public List<Usuario> getUsersWithoutNotification() {
        List<Usuario> listUsuarios = getAll();
        List<Usuario> listResult = new ArrayList<>();
        listUsuarios.stream().filter(u -> u.getNotificaciones().isEmpty()).forEach(user -> {
            listResult.add(user);
        });
        return listResult;
    }

    @Override
    public Usuario deleteById(Long id) {
        Optional<Usuario> optUsuario = repository.findById(id);
        if (!optUsuario.isPresent()) {
            return null;
        }
        repository.deleteById(id);
        return optUsuario.get();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<Notificacion> assignUser(Usuario usuario, List<Integer> listNotificationsId) {
        List<Notificacion> assignedNotifications = new ArrayList<>();
        listNotificationsId.forEach(x -> {
            Optional<Notificacion> notificacion = notificacionRepository.findById(x.longValue());
            if (notificacion.isPresent()) {
                if (notificacion.get().usuarios.size() < 5) {
                    assignedNotifications.add(notificacion.get());
                    usuario.addNotification(notificacion.get());
                }
            }
        });
        repository.save(usuario);
        return assignedNotifications;
    }

    @Override
    public List<Notificacion> unassignUser(Usuario usuario, List<Integer> listNotificationsId) {
        List<Notificacion> unassignedNotifications = new ArrayList<>();
        listNotificationsId.forEach(x -> {
            Optional<Notificacion> notificacion = notificacionRepository.findById(x.longValue());
            if (notificacion.isPresent()) {
                if (notificacion.get().usuarios.contains(usuario) && !notificacion.get().usuarios.isEmpty()) {
                    unassignedNotifications.add(notificacion.get());
                    usuario.deleteNotification(notificacion.get());
                }
            }
        });
        repository.save(usuario);
        return unassignedNotifications;
    }
}

