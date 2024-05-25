package com.bank.authorization.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class PassportDtoTest {

    @Test
    void testEqualsAndHashCode() {
        PassportDto passport1 = new PassportDto(1L, 123, 456789L, "Doe", "John", "Smith", "Male",
                LocalDate.of(1990, 5, 15), "New York", "Department of State",
                LocalDate.of(2010, 10, 20), 789012, LocalDate.of(2030, 5, 15), new RegistrationDto());
        PassportDto passport2 = new PassportDto(1L, 123, 456789L, "Doe", "John", "Smith", "Male",
                LocalDate.of(1990, 5, 15), "New York", "Department of State",
                LocalDate.of(2010, 10, 20), 789012, LocalDate.of(2030, 5, 15), new RegistrationDto());

        assertThat(passport1).isEqualTo(passport2);
        assertThat(passport1.hashCode()).isEqualTo(passport2.hashCode());
    }


}