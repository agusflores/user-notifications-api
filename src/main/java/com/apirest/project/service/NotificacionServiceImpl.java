package com.apirest.project.service;

import com.apirest.project.dtos.CreateNotificationDTO;
import com.apirest.project.dtos.UpdateNotificationDTO;
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
public class NotificacionServiceImpl implements INotificacionService {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createNotification(CreateNotificationDTO notificationDTO) {
        Notificacion notificacion = ApiHelper.createNotificationToEntity(notificationDTO);
        repository.save(notificacion);
    }

    @Override
    public void updateNotification(UpdateNotificationDTO notificationDTO) {
        Optional<Notificacion> optNotification = repository.findById(notificationDTO.getId());
        if (optNotification.isPresent()) {
            Notificacion notification = ApiHelper.updateNotificationFunction(optNotification.get(), notificationDTO);
            repository.save(notification);
        }
    }

    @Override
    public void updateNotification(Notificacion notificacion) {
        repository.save(notificacion);
    }

    @Override
    public List<Notificacion> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Notificacion> getNotificationsWithoutUser() {

        List<Notificacion> listNotifications = getAll();
        List<Notificacion> result = new ArrayList<>();
        listNotifications.stream().filter(n -> n.getUsuarios().size() == 0).forEach(n -> {
            result.add(n);
        });

        return result;
    }

    @Override
    public List<Notificacion> getNotificationsByString(String string) {
        List<Notificacion> listNotifications = getAll();
        List<Notificacion> listResult = new ArrayList<>();
        listNotifications.stream().filter(n ->
                        n.getContenido().contains(string))
                .forEach(n -> {
                    listResult.add(n);
                });
        return listResult;
    }

    @Override
    public List<Notificacion> findNotificationsByUserId(Long userId) {
        List<Notificacion> notificacionList = getAll();
        Optional<Usuario> user = userRepository.findById(userId);
        List<Notificacion> result = new ArrayList<>();
        if (user.isPresent()) {
            notificacionList.stream().filter(n -> n.getUsuarios().contains(user)).forEach(n -> {
                result.add(n);
            });
        }
        return result;
    }

    @Override
    public List<Notificacion> searchNotificationsByUsername(String username) {
        List<Notificacion> notificacionList = getAll();
        Optional<Usuario> user = userRepository.findByUsername(username);
        List<Notificacion> result = new ArrayList<>();
        if (user.isPresent()) {
            notificacionList.stream().filter(n -> n.getUsuarios().contains(user)).forEach(n -> {
                result.add(n);
            });
        }
        return result;
    }

    @Override
    public Notificacion getById(Long id) {
        Optional<Notificacion> notificacion = repository.findById(id);
        return notificacion.orElse(null);
    }

    @Override
    public Notificacion deleteById(Long id) {
        Optional<Notificacion> optNotification = repository.findById(id);
        if (!optNotification.isPresent()) {
            return null;
        }
        repository.deleteById(id);
        return optNotification.get();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
