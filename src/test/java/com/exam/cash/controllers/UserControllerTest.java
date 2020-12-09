package com.exam.cash.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.exam.cash.model.User;
import com.exam.cash.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    
    @Autowired                           
    private MockMvc mockMvc; 
    
    @MockBean
    private UserService userService;

    @Test
    public void shouldGetAllUsers() throws Exception {

        List<User> users = new ArrayList<>();
        users.add(new User(1l,"email1", "name1", "surname1"));
        users.add(new User(2l,"email2", "name2", "surname2"));
        users.add(new User(3l,"email3", "name3", "surname3"));

        when(userService.getAllUsers()).thenReturn(users);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                  .get("/users"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        assertEquals(asJsonString(users), result.getResponse().getContentAsString());
    }

    @Test
    public void shouldGetOneUser() throws Exception {

        List<User> users = new ArrayList<>();
        users.add(new User(1l,"email1", "name1", "surname1"));

        when(userService.getAllUsers()).thenReturn(users);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                  .get("/users"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        assertEquals(asJsonString(users), result.getResponse().getContentAsString());
    }

    @Test
    public void shouldGetNoUsers() throws Exception {

        List<User> users = new ArrayList<>();

        when(userService.getAllUsers()).thenReturn(users);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                  .get("/users"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        assertEquals(asJsonString(users), result.getResponse().getContentAsString());
    }

    @Test
    public void shouldGetSpecifiedUser() throws Exception {

        User user1 = new User(1l, "email1", "name1", "surname1");

        when(userService.getUser(1l)).thenReturn(Optional.of(user1));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                  .get("/users/1"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        assertEquals(asJsonString(user1), result.getResponse().getContentAsString());
    }
    
    @Test
    public void shouldSaveUser() throws Exception {

        User user1 = new User(1l, "email1", "name1", "surname1");

        when(userService.save(any(User.class))).thenReturn(Optional.of(user1));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                  .post("/users")
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(asJsonString(user1)))
                                  .andExpect(status().isOk())
                                  .andReturn();

        assertEquals(asJsonString(user1), result.getResponse().getContentAsString());
    }

    @Test
    public void shouldNotSaveUser() throws Exception {

        User user1 = new User(1l, "email1", "name1", "surname1");

        when(userService.save(any(User.class))).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
               .post("/users")
               .contentType(MediaType.APPLICATION_JSON)
               .content(asJsonString(user1)))
               .andExpect(status().isConflict());
    }

    @Test
    public void shouldDeleteUser() throws Exception {

        User user1 = new User(1l, "email1", "name1", "surname1");

        when(userService.getUser(1l)).thenReturn(Optional.of(user1));

        mockMvc.perform(MockMvcRequestBuilders
               .delete("/users/1"))
               .andExpect(status().isNoContent());
    }

    @Test
    public void shouldNotDeleteUser() throws Exception {

        when(userService.getUser(1l)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
               .delete("/users/1"))
               .andExpect(status().isNoContent());
    }

    private static String asJsonString(Object someObject) {
        try {
            return new ObjectMapper().writeValueAsString(someObject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}