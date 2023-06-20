package ru.homework.hometask07.controller.dto;

import java.time.LocalDate;
import java.util.List;
public record FilmDto(
        Integer id,
        String title,
        LocalDate premierDate,
        String country,
        String genre,
        List<Integer> directorIDs
) {
}
