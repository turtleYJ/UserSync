package com.example.usersync.service;

import com.example.usersync.model.User;
import com.example.usersync.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("John Doe");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createdUser(user);
        assertThat(createdUser.getName()).isEqualTo("John Doe");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById(1L);
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("John Doe");
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        when(userRepository.save(any(User.class))).thenReturn(user);

        user.setName("Jane Doe");
        User updatedUser = userService.updateUser(user);
        assertThat(updatedUser.getName()).isEqualTo("Jane Doe");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}
