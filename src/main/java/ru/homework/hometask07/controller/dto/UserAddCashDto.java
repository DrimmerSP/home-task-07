package ru.homework.hometask07.controller.dto;

import lombok.Data;

@Data
public class UserAddCashDto {
    private Long id;
    private String login;
    private Long cash;
    private Long updateCashValue;
}
