package ru.homework.hometask07.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto extends GenericDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private String phone;
    private String address;
    private String email;
}
