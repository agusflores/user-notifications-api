package com.apirest.project.controller;

import com.apirest.project.dtos.CreateUserDTO;
import com.apirest.project.dtos.UpdateUserDTO;
import com.apirest.project.dtos.ViewUserDTO;
import com.apirest.project.entities.Usuario;
import com.apirest.project.helper.ApiHelper;
import com.apirest.project.helper.ResponseBase;
import com.apirest.project.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUsuarioService service;


    @GetMapping("/users")
    public ResponseEntity<List<ViewUserDTO>> getAll() {
        List<Usuario> listUsuarios = service.getAll();
        List<ViewUserDTO> result = ApiHelper.listViewUsersDTO(listUsuarios);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ViewUserDTO> getUserById(@PathVariable Long userId) {
        Usuario user = service.getById(userId);
        if (user != null) {
            ViewUserDTO result = ApiHelper.userViewUserDTO(user);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users/get-by-username/{username}")
    public ResponseEntity<ViewUserDTO> getUserByUsername(@PathVariable String username) {
        Usuario user = service.getByUsername(username);
        if (user != null) {
            ViewUserDTO result = ApiHelper.userViewUserDTO(user);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users/users-without-notification")
    public ResponseEntity<List<ViewUserDTO>> getUsersWithoutNotification() {
        List<Usuario> listUsuarios = service.getUsersWithoutNotification();
        List<ViewUserDTO> resultList = ApiHelper.listViewUsersDTO(listUsuarios);
        if (!resultList.isEmpty()) {
            return ResponseEntity.ok(resultList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users/create-user")
    public ResponseEntity<ResponseBase> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        if (!service.repeatedUsername(createUserDTO.getUsername())) {
            service.createUser(createUserDTO);
            return ApiHelper.responseOk();
        } else {
            return ApiHelper.responseNotPossible();
        }
    }

    @PostMapping("/users/assign-user/{userId}")
    public ResponseEntity<ResponseBase> assignUser(@Valid @PathVariable Long userId, @RequestBody List<Integer> listNotificationsId) {
        Usuario result = service.getById(userId);
        if (service.assignUser(result, listNotificationsId).size() == listNotificationsId.size()) {
            return ApiHelper.responseOk();
        }else if(service.assignUser(result, listNotificationsId).isEmpty()){
            return ApiHelper.responseNotFound();
        }else {
            return ApiHelper.responseNotPossible();
        }
    }

    @PostMapping("/users/unassign-user/{userId}")
    public ResponseEntity<ResponseBase> unassignUser(@Valid @PathVariable Long userId, @RequestBody List<Integer> listNotificationsId) {
        Usuario result = service.getById(userId);
        if (service.unassignUser(result, listNotificationsId).size() ==listNotificationsId.size()) {
            return ApiHelper.responseOk();
        }else if(service.unassignUser(result,listNotificationsId).isEmpty()) {
            return ApiHelper.responseNotFound();
        }else {
            return ApiHelper.responseNotPossible();
        }
    }

    @PutMapping("/users/update-user")
    public ResponseEntity<ResponseBase> updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO) {
        if (!service.repeatedUsername(updateUserDTO.getUsername())) {
            service.updateUser(updateUserDTO);
            return ApiHelper.responseOk();
        } else {
            return ApiHelper.responseNotPossible();
        }
    }

    @DeleteMapping("users/delete-user/{id}")
    public ResponseEntity<ResponseBase> deleteFromPathVariable(@PathVariable Long id) {
        Usuario result = service.deleteById(id);
        if (result == null) {
            return ApiHelper.responseNotFound();
        }
        return ApiHelper.responseOk();
    }

    @DeleteMapping("users/delete-user")
    public ResponseEntity<ResponseBase> deleteFromRequestParam(@RequestParam Long id) {
        Usuario result = service.deleteById(id);
        if (result == null) {
            return ApiHelper.responseNotFound();
        }
        return ApiHelper.responseOk();
    }

    @DeleteMapping("users/delete-all")
    public ResponseEntity<ResponseBase> deleteAll() {
        service.deleteAll();
        return ApiHelper.responseOk();
    }
}
