package com.bank.authorization.controller;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.repository.UserRepository;
import com.bank.authorization.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = "classpath:/application-local.yaml")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUser() throws Exception {
        UserDto userDto = new UserDto(/* your test data here */);
        when(userService.save(any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }




/*
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
    }*/
}