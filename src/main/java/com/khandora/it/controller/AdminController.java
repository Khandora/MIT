package com.khandora.it.controller;

import com.khandora.it.dto.AdminUserDto;
import com.khandora.it.model.User;
import com.khandora.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "user/all")
    public ResponseEntity<List<AdminUserDto>> getAllUsers() {
        List<User> users = userService.getAll();
        List<AdminUserDto> result = new ArrayList<>();

        if (users == null || users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        users.forEach(user -> result.add(AdminUserDto.fromUser(user)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        if (user != null) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}