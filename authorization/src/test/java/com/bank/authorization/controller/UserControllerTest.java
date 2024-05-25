package com.bank.authorization.controller;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;


    @Test
    void create() {
        UserDto userDto = new UserDto();
        when(userService.save(any(UserDto.class))).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.create(userDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }


    @Test
    void read() {
        Long id = 1L;
        UserDto userDto = new UserDto();
        when(userService.findById(id)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.read(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());

    }

    @Test
    void update() {
        Long id = 1L;
        UserDto userDto = new UserDto();
        when(userService.update(id, userDto)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.update(id, userDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void readAll() {
        List<Long> usersIds = Arrays.asList(1L, 2L, 3L);
        List<UserDto> userDtos = Arrays.asList(new UserDto(), new UserDto(), new UserDto());
        when(userService.findAllByIds(usersIds)).thenReturn(userDtos);

        ResponseEntity<List<UserDto>> response = userController.readAll(usersIds);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDtos, response.getBody());
    }
}