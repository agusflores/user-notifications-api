package com.apirest.project.service;

import com.apirest.project.dtos.CreateNotificationDTO;
import com.apirest.project.dtos.UpdateNotificationDTO;
import com.apirest.project.entities.Notificacion;

import java.util.List;

public interface INotificacionService {
    public void createNotification(CreateNotificationDTO notificationDTO);

    public void updateNotification(UpdateNotificationDTO notificationDTO);

    public void updateNotification(Notificacion notificacion);

    public List<Notificacion> getAll();

    public List<Notificacion> getNotificationsWithoutUser();

    public List<Notificacion> getNotificationsByString(String string);

    public List<Notificacion> findNotificationsByUserId(Long id);

    public List<Notificacion> searchNotificationsByUsername(String username);

    public Notificacion getById(Long id);

    public Notificacion deleteById(Long id);

    public void deleteAll();
}
