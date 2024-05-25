package com.bank.authorization.service;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.dto.UserDto;
import com.bank.authorization.entity.AuditEntity;
import com.bank.authorization.mapper.AuditMapper;
import com.bank.authorization.repository.AuditRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuditServiceImplTest {

    @Mock
    private AuditRepository auditRepository;

    @Mock
    private AuditMapper auditMapper;

    @InjectMocks
    private AuditServiceImpl auditService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void findById() {
        Long id = 1L;
        AuditDto auditDto = new AuditDto();
        auditDto.setId(id);
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setId(id);

        when(auditRepository.findById(id)).thenReturn(Optional.of(auditEntity));
        when(auditMapper.toDto(auditEntity)).thenReturn(auditDto);

        AuditDto result = auditService.findById(id);
        assertEquals(auditDto, result);

        verify(auditRepository, times(1)).findById(id);
        verify(auditMapper, times(1)).toDto(auditEntity);


    }

    @Test
    void findById_WhenEntityNotExists() {
        Long id = 1L;
        when(auditRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> auditService.findById(id));
        verify(auditRepository, times(1)).findById(id);
    }

}