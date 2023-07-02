package ru.homework.hometask07.controller.dto;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends GenericDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private String phone;
    private String address;
    private String email;
    private Long roleID;
}
