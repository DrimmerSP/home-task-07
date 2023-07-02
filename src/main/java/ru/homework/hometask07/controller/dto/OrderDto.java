package ru.homework.hometask07.controller.dto;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public record OrderDto(
        Integer id,
        Integer userID,
        List<Integer> filmID,
        LocalDate rentFrom,
        Duration rentPeriod,
        Boolean purchase,
        FilmDto filmDto
) {
}
