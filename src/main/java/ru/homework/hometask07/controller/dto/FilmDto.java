package ru.homework.hometask07.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.homework.hometask07.model.Country;
import ru.homework.hometask07.model.Genre;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public record FilmDto(
        Integer filmId,
        String title,
        LocalDate premierDate,
        Integer seriesCount,
        Country country,
        Genre genre,
        String description,
        List<Integer> directorIDs,
        List<DirectorDto> directorInfo
) {
}
