package ru.homework.hometask07.controller.dto;

import java.time.LocalDate;

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
        LocalDate createdWhen,
        Integer roleID
) {
}
