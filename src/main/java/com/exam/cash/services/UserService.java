package com.exam.cash.services;

import java.util.List;
import java.util.Optional;

import com.exam.cash.model.User;
import com.exam.cash.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired(required = true)
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUser(Long userId) {
        return repository.findById(userId);
    }
    
    public Optional<User> save(User newUser) {
        return repository.findByEmail(newUser.getEmail()).isPresent() ? Optional.empty()
                                                                      : Optional.of(repository.save(newUser));
    }

    public void delete(User user) {
        repository.delete(user);
    }
}
