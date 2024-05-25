package com.bank.authorization.controller;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.service.AuditService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuditControllerTest {

    @Mock
    private AuditService service;

    @InjectMocks
    private AuditController auditController;

    @Test
    public void read() {
        AuditDto expectedAuditDto = new AuditDto();
        when(service.findById(1L)).thenReturn(expectedAuditDto);
        AuditDto result = auditController.read(1L);
        assertEquals(expectedAuditDto, result);
    }
}