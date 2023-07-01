package ru.homework.hometask07.controller.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private String phone;
    private String address;
    private String email;
    private LocalDateTime createdWhen;
    private Integer roleID;
}
