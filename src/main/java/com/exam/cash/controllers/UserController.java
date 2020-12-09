
package com.exam.cash.controllers;

import java.util.List;
import java.util.Optional;

import com.exam.cash.services.UserService;
import com.exam.cash.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;
    
    //------------GET METHODS------------//

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {

        Optional<User> found = service.getUser(userId);

        return found.isPresent() ? new ResponseEntity<>(found.get(), HttpStatus.OK)
                                 : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //------------POST METHODS------------//
    
    @PostMapping(value = "/users")
    public ResponseEntity<User> save(@RequestBody User newUser) {

        Optional<User> saved = service.save(newUser);

        return saved.isPresent() ? new ResponseEntity<>(saved.get(), HttpStatus.OK)
                                 : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    //------------DELETE METHODS------------//
    
    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<User> delete(@PathVariable Long userId) {

        Optional<User> found = service.getUser(userId);
        if(found.isPresent()) {
            service.delete(found.get());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
