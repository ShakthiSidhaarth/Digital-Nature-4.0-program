package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById_UserExists() {
        User user = new User(1L, "Tharun");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User result = userService.getUserById(1L);
        assertNotNull(result);
        assertEquals("Tharun", result.getName());
    }
    @Test
    void testGetUserById_UserDoesNotExist() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        User result = userService.getUserById(2L);
        assertNull(result);
    }
}
