package com.bank.authorization.controller;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.entity.AuditEntity;
import com.bank.authorization.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для {@link AuditEntity}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/audit")
public class AuditController {

    private final AuditService service;

    /**
     * @param id технический идентификатор {@link AuditEntity}
     * @return {@link ResponseEntity <AuditDto>}
     */
    @GetMapping("/{id}")
    @ExceptionHandler
    public AuditDto read(@PathVariable("id") Long id) {
        return service.findById(id);
    }
}
