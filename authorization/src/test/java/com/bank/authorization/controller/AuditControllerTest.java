package com.bank.authorization.controller;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.repository.AuditRepository;
import com.bank.authorization.service.AuditService;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuditController.class)
class AuditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditService auditService;

    @Test
    public void testReadAuditEntity() throws Exception {
        Long auditId = 1L;
        AuditDto auditDto = new AuditDto();

        when(auditService.findById(auditId)).thenReturn(auditDto);

        mockMvc.perform(get("/audit/{id}", auditId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}























   /* @Mock
    private AuditService service;

    @InjectMocks
    private AuditController auditController;

    @Test
    public void read() {
        AuditDto expectedAuditDto = new AuditDto();
        when(service.findById(1L)).thenReturn(expectedAuditDto);
        AuditDto result = auditController.read(1L);
        assertEquals(expectedAuditDto, result);
    }*/



