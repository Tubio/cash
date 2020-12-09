package com.exam.cash.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import com.exam.cash.model.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void shouldFindAllUsers() {

        User testUser1 = new User();
        testUser1.setEmail("email1");
        testUser1.setFirstName("name1");
        testUser1.setLastName("surname1");

        User testUser2 = new User();
        testUser2.setEmail("email2");
        testUser2.setFirstName("name2");
        testUser2.setLastName("surname2");

        entityManager.persist(testUser1);
        entityManager.persist(testUser2);

        assertEquals(Arrays.asList(testUser1, testUser2), userRepository.findAll());
    }

    @Test
    public void shouldFindOneUser() {

        User testUser1 = new User();
        testUser1.setEmail("email");
        testUser1.setFirstName("name");
        testUser1.setLastName("surname");

        entityManager.persist(testUser1);

        assertEquals(Arrays.asList(testUser1), userRepository.findAll());
    }

    @Test
    public void shouldFindNoUsers() {
        assertEquals(new ArrayList<>(), userRepository.findAll());
    }

    @Test
    public void shouldFindSpecifiedUserById() {

        User testUser1 = new User();
        testUser1.setEmail("email");
        testUser1.setFirstName("name");
        testUser1.setLastName("surname");

        entityManager.persist(testUser1);

        assertEquals(Optional.of(testUser1), userRepository.findById(testUser1.getId()));
    }

    @Test
    public void shouldNotFindSpecifiedUserById() {
        assertEquals(Optional.empty(), userRepository.findById(1l));
    }

    @Test
    public void shouldFindSpecifiedUserByEmail() {

        User testUser1 = new User();
        testUser1.setEmail("email");
        testUser1.setFirstName("name");
        testUser1.setLastName("surname");

        entityManager.persist(testUser1);

        assertEquals(Optional.of(testUser1), userRepository.findByEmail(testUser1.getEmail()));
    }

    @Test
    public void shouldNotFindSpecifiedUserByEmail() {
        assertEquals(Optional.empty(), userRepository.findByEmail("email"));
    }

    @Test
    public void shouldDeleteUser() {

        User testUser = new User();
        testUser.setEmail("email");
        testUser.setFirstName("name");
        testUser.setLastName("surname");

        entityManager.persist(testUser);
        userRepository.delete(testUser);

        assertEquals(Optional.empty(), userRepository.findById(testUser.getId()));
    }
}
