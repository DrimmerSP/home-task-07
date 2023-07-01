package ru.homework.hometask07.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class LoginDto {
    private String login;
    private String password;
}
