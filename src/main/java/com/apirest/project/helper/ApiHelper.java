package com.apirest.project.helper;

import com.apirest.project.dtos.*;
import com.apirest.project.entities.Notificacion;
import com.apirest.project.entities.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ApiHelper {

    private ApiHelper() {}

    public static ResponseEntity<ResponseBase> responseNotFound() {
        ResponseBase response = new ResponseBase();
        response.setCabecera("NOT FOUND");
        response.setMensaje("No se pudo encontrar la entidad solicitada.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<ResponseBase> responseNotPossible() {
        ResponseBase response = new ResponseBase();
        response.setCabecera("ERROR");
        response.setMensaje("No se pudo realizar la accion solicitada");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ResponseBase> responseOk() {
        ResponseBase response = new ResponseBase();
        response.setCabecera("OK");
        response.setMensaje("Se ha ejecutado la accion correctamente!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static Usuario createUserToEntity(CreateUserDTO userDTO) {
        return new Usuario(userDTO.getUsername(), userDTO.getPassword());
    }

    public static ViewUserDTO createUserToDto(Usuario usuario, List<ViewListNotificationsDTO> listNotificaciones) {
        return new ViewUserDTO(usuario.getId(), usuario.getUsername(), usuario.getPassword(), listNotificaciones);
    }

    public static Usuario updateUserFunction(Usuario user, UpdateUserDTO dto) {
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static Notificacion createNotificationToEntity(CreateNotificationDTO notificationDTO) {
        return new Notificacion(notificationDTO.getContenido());
    }

    public static Notificacion updateNotificationFunction(Notificacion notificacion, UpdateNotificationDTO dto) {
        notificacion.setContenido(dto.getContenido());
        return notificacion;
    }

    public static List<ViewUserDTO> listViewUsersDTO(List<Usuario> listUsers) {
        List<ViewUserDTO> resultList = new ArrayList<>();
        listUsers.forEach(user -> {
            List<ViewListNotificationsDTO> listNotificationsDTOS =
                    ApiHelper.convertListNotificationsToDTO(user.notificaciones);
            resultList.add(new ViewUserDTO(user, listNotificationsDTOS));
        });
        return resultList;
    }

    public static ViewUserDTO userViewUserDTO(Usuario user) {
        List<ViewListNotificationsDTO> listNotificationsDTOS =
                ApiHelper.convertListNotificationsToDTO(user.notificaciones);
        return new ViewUserDTO(user, listNotificationsDTOS);
    }

    public static ViewNotificationDTO notificationViewNotificationDTO(Notificacion notification) {
        List<ViewListUsersDTO> listUsersDTO =
                ApiHelper.convertListUsersToDTO(notification.usuarios);
        return new ViewNotificationDTO(notification, listUsersDTO);
    }

    public static List<ViewNotificationDTO> listViewNotificationsDTO(List<Notificacion> listNotifications) {
        List<ViewNotificationDTO> resultList = new ArrayList<>();
        listNotifications.forEach(notificacion -> {
            List<ViewListUsersDTO> listUsersDTOS =
                    ApiHelper.convertListUsersToDTO(notificacion.usuarios);
            resultList.add(new ViewNotificationDTO(notificacion, listUsersDTOS));
        });
        return resultList;
    }

    public static List<ViewListNotificationsDTO> convertListNotificationsToDTO(List<Notificacion> listNotifications) {
        List<ViewListNotificationsDTO> resultList = new ArrayList<>();
        listNotifications.forEach(notificacion -> {
            resultList.add(new ViewListNotificationsDTO(notificacion));
        });
        return resultList;
    }

    public static List<ViewListUsersDTO> convertListUsersToDTO(List<Usuario> listUsers) {
        List<ViewListUsersDTO> resultList = new ArrayList<>();
        listUsers.forEach(usuario -> {
            resultList.add(new ViewListUsersDTO(usuario));
        });
        return resultList;
    }
}
