package ru.homework.hometask07.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserDto(
        Integer id,
        String login,
        String password,
        String firstName,
        String lastName,
        String middleName,
        LocalDate birthDate,
        String phone,
        String address,
        String email,
        LocalDateTime createdWhen,
        Integer roleID
) {
}
