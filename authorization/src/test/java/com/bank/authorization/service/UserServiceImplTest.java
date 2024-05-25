package com.bank.authorization.service;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.entity.UserEntity;
import com.bank.authorization.mapper.UserMapper;
import com.bank.authorization.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Long id = 1L;
        UserDto userDto = new UserDto();
        UserEntity userEntity = new UserEntity();

        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
        when(userMapper.toDTO(userEntity)).thenReturn(userDto);
        UserDto result = userService.findById(id);
        assertEquals(userDto, result);
        verify(userRepository).findById(id);
        verify(userMapper).toDTO(userEntity);
    }

    @Test
    void testFindById() {
        Long userId = 1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        UserDto userDto = new UserDto();
        when(userMapper.toDTO(userEntity)).thenReturn(userDto);
        UserDto result = userService.findById(userId);
        assertEquals(userDto, result);
    }

    @Test
    public void testUserPassword() {
        UserEntity user = new UserEntity();
        user.setPassword("password123");
        assertNotNull(user);
        String password = user.getPassword();
        assertNotNull(password);
    }


    @Test
    void save() {
        UserDto userDto = new UserDto();
        userDto.setPassword("password123");

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("password123");

        when(userMapper.toEntity(userDto)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        UserDto result = userService.save(userDto);

        assertNotNull(userDto);
        verify(userRepository, times(1)).save(any(UserEntity.class));


    }


    @Test
    void testUpdate_WhenUserExists() {

        Long userId = 1L;
        UserDto userDto = new UserDto();
        UserEntity userEntity = new UserEntity();
        UserEntity updatedUserEntity = new UserEntity();
        UserDto expectedUserDto = new UserDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(updatedUserEntity);
        when(userMapper.mergeToEntity(userDto, userEntity)).thenReturn(updatedUserEntity);
        when(userMapper.toDTO(updatedUserEntity)).thenReturn(expectedUserDto);


        UserDto actualUserDto = userService.update(userId, userDto);

        assertEquals(expectedUserDto, actualUserDto);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(updatedUserEntity);
        verify(userMapper, times(1)).mergeToEntity(userDto, userEntity);
        verify(userMapper, times(1)).toDTO(updatedUserEntity);
    }

    @Test
    void testSaveUser_NullInput() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            userService.save(null);
        });

        assertEquals(NullPointerException.class, exception.getClass());
        verifyNoInteractions(userRepository);


    }


    @Test
    void update() {

        Long id = 1L;
        UserDto userDto = new UserDto();
        UserEntity userEntity = new UserEntity();

        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
        when(userMapper.mergeToEntity(userDto, userEntity)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.toDTO(userEntity)).thenReturn(userDto);

        UserDto result = userService.update(id, userDto);

        verify(userRepository).findById(id);
        verify(userMapper).mergeToEntity(userDto, userEntity);
        verify(userRepository).save(userEntity);
        verify(userMapper).toDTO(userEntity);

        assertNotNull(result);
        assertEquals(userDto, result);


    }

    @Test
    void findAllByIds() {
        List<Long> userIds = List.of(1L, 2L, 3L);
        List<UserEntity> userEntities = new ArrayList<>();
        List<UserDto> expectedUserDtos = new ArrayList<>();

        for (Long userId : userIds) {
            UserEntity userEntity = new UserEntity();
            userEntities.add(userEntity);
            UserDto userDto = new UserDto();
            expectedUserDtos.add(userDto);

            when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
            when(userMapper.toDTO(userEntity)).thenReturn(userDto);
        }

        List<UserDto> actualUserDtos = userService.findAllByIds(userIds);
        assertNotNull(actualUserDtos);
        for (Long userId : userIds) {
            verify(userRepository, times(1)).findById(userId);
        }
    }

}
