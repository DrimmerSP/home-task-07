package ru.homework.hometask07.controller.dto;

import ru.homework.hometask07.model.Country;
import ru.homework.hometask07.model.Genre;

import java.time.LocalDate;
import java.util.List;

public record FilmDto(
        Integer id,
        String title,
        LocalDate premierDate,
        Country country,
        Genre genre,
        String description,
        List<Integer> directorIDs
) {
}
