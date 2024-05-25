package com.bank.authorization.mapper;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.entity.AuditEntity;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AuditMapperImplTest {

    AuditMapperImpl auditMapper = new AuditMapperImpl();

    @Test
    void toDto() {
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setId(1L);
        auditEntity.setEntityType("User");
        auditEntity.setOperationType("CREATE");
        auditEntity.setCreatedBy("admin");
        auditEntity.setModifiedBy("admin");
        auditEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        auditEntity.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
        auditEntity.setNewEntityJson("{\"name\": \"John Doe\"}");
        auditEntity.setEntityJson("{\"name\": \"Jane Doe\"}");


        AuditDto auditDto = auditMapper.toDto(auditEntity);

        assertNotNull(auditDto);
        assertEquals(auditEntity.getId(), auditDto.getId());
        assertEquals(auditEntity.getEntityType(), auditDto.getEntityType());
        assertEquals(auditEntity.getOperationType(), auditDto.getOperationType());
        assertEquals(auditEntity.getCreatedBy(), auditDto.getCreatedBy());
        assertEquals(auditEntity.getModifiedBy(), auditDto.getModifiedBy());
        assertEquals(auditEntity.getCreatedAt(), auditDto.getCreatedAt());
        assertEquals(auditEntity.getModifiedAt(), auditDto.getModifiedAt());
        assertEquals(auditEntity.getNewEntityJson(), auditDto.getNewEntityJson());
        assertEquals(auditEntity.getEntityJson(), auditDto.getEntityJson());
    }
}
