package com.bank.authorization.mapper;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMapperImplTest {


    UserMapperImpl userMapper = new UserMapperImpl();



    @Test
    void toDTO() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setRole("ROLE_USER");
        userEntity.setPassword("pass");
        userEntity.setProfileId(2L);

        UserDto userDto = userMapper.toDTO(userEntity);

        assertNotNull(userDto);
        assertEquals(userDto.getId(), userEntity.getId());
        assertEquals(userDto.getRole(), userEntity.getRole());
        assertEquals(userDto.getPassword(), userEntity.getPassword());
        assertEquals(userDto.getProfileId(), userEntity.getProfileId());
    }

    @Test
    void toEntity() {
        UserDto userDto = new UserDto();
        userDto.setRole("ROLE_USER");
        userDto.setProfileId(2L);
        userDto.setPassword("pass");

        UserEntity res = userMapper.toEntity(userDto);


        assertNotNull(res);
        assertEquals(res.getRole(), userDto.getRole());
        assertEquals(res.getPassword(), userDto.getPassword());
        assertEquals(res.getProfileId(), userDto.getProfileId());


    }

    @Test
    void toDtoList() {

        List<UserEntity> userEntityList = new ArrayList<>();
        userEntityList.add(new UserEntity());
        userEntityList.add(new UserEntity());

        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(new UserDto());
        userDtoList.add(new UserDto());

        List<UserDto> actual = userMapper.toDtoList(userEntityList);

        assertEquals(actual, userDtoList);

    }

    @Test
    void mergeToEntity() {
        UserDto userDto = new UserDto();
        UserEntity userEntity = new UserEntity();


        UserEntity merged = userMapper.mergeToEntity(userDto, userEntity);

        assertNotNull(merged);
        assertEquals(merged.getId(), userEntity.getId());
        assertEquals(merged.getRole(), userEntity.getRole());

    }
}