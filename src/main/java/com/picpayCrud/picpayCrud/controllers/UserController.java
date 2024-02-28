package com.picpayCrud.picpayCrud.controllers;

import com.picpayCrud.picpayCrud.model.user.UserModel;
import com.picpayCrud.picpayCrud.dtos.UserDTO;
import com.picpayCrud.picpayCrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserModel> createUser(@RequestBody UserDTO user) {
        UserModel newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        try {
            UserModel user = this.userService.findUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> inactivateUserById(@PathVariable Long id) {
        try {
            String message = userService.inactivateUserById(id);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel newUser) {
        try {
            UserModel updatedUser = userService.updateUser(id, newUser);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
