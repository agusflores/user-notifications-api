package com.apirest.project.controller;

import com.apirest.project.dtos.*;
import com.apirest.project.entities.Notificacion;
import com.apirest.project.helper.ApiHelper;
import com.apirest.project.helper.ResponseBase;
import com.apirest.project.repository.UserRepository;
import com.apirest.project.service.INotificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private INotificacionService service;

    @Autowired
    private UserRepository repositoryUser;

    @GetMapping("/notifications")
    public ResponseEntity<List<ViewNotificationDTO>> getAll() {
        List<Notificacion> listNotificaciones = service.getAll();
        List<ViewNotificationDTO> result = ApiHelper.listViewNotificationsDTO(listNotificaciones);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity<ViewNotificationDTO> getNotificationById(@PathVariable Long id) {
        Notificacion notificacion = service.getById(id);
        if (notificacion != null) {
            ViewNotificationDTO result = ApiHelper.notificationViewNotificationDTO(notificacion);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/notifications/notifications-sin-usuario")
    public ResponseEntity<List<ViewNotificationDTO>> getNotificationsWithoutUser() {
        List<Notificacion> listNotificaciones = service.getNotificationsWithoutUser();
        List<ViewNotificationDTO> resultList = ApiHelper.listViewNotificationsDTO(listNotificaciones);
        if (!resultList.isEmpty()) {
            return ResponseEntity.ok(resultList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/notifications/findByUserId/{userId}")
    public ResponseEntity<List<ViewNotificationDTO>> getNotificationsByUserId(@PathVariable Long userId) {
        List<Notificacion> listNotificaciones = service.findNotificationsByUserId(userId);
        List<ViewNotificationDTO> resultList = ApiHelper.listViewNotificationsDTO(listNotificaciones);
        if (!resultList.isEmpty()) {
            return ResponseEntity.ok(resultList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/notifications/findByUsername/{username}")
    public ResponseEntity<List<ViewNotificationDTO>> getNotificationsByUserName(@PathVariable String username) {
        List<Notificacion> listNotificaciones = service.searchNotificationsByUsername(username);
        List<ViewNotificationDTO> resultList = ApiHelper.listViewNotificationsDTO(listNotificaciones);
        if (!resultList.isEmpty()) {
            return ResponseEntity.ok(resultList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/notifications/create-notification")
    public ResponseEntity<ResponseBase> createNotification(@Valid @RequestBody CreateNotificationDTO createNotificationDTO) {
        service.createNotification(createNotificationDTO);
        return ApiHelper.responseOk();
    }

    @PutMapping("/notifications/update-notification")
    public ResponseEntity<ResponseBase> updateNotification(@Valid @RequestBody UpdateNotificationDTO updateNotificationDTO) {
        service.updateNotification(updateNotificationDTO);
        return ApiHelper.responseOk();
    }

    @DeleteMapping("notifications/delete-notification/{id}")
    public ResponseEntity<ResponseBase> deleteFromPathVariable(@PathVariable Long id) {
        Notificacion result = service.deleteById(id);
        if (result == null) {
            return ApiHelper.responseNotFound();
        }
        return ApiHelper.responseOk();
    }

    @DeleteMapping("notifications/delete-notification")
    public ResponseEntity<ResponseBase> deleteFromRequestParam(@RequestParam Long id) {
        Notificacion result = service.deleteById(id);
        if (result == null) {
            return ApiHelper.responseNotFound();
        }
        return ApiHelper.responseOk();
    }

    @GetMapping("/notifications/find-by-string/{string}")
    public ResponseEntity<List<ViewNotificationDTO>> searchString(@PathVariable String string) {
        List<Notificacion> listResult = service.getNotificationsByString(string);
        List<ViewNotificationDTO> result = ApiHelper.listViewNotificationsDTO(listResult);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("notifications/delete-all")
    public ResponseEntity<ResponseBase> deleteAll() {
        service.deleteAll();
        return ApiHelper.responseOk();
    }
}
