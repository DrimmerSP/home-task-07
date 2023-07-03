package ru.homework.hometask07.controller.dto;

import lombok.*;
import ru.homework.hometask07.model.Country;
import ru.homework.hometask07.model.Genre;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public final class FilmDto extends GenericDto {
    private String title;
    private LocalDate premierDate;
    private Country country;
    private Genre genre;
    private String description;
    private List<Long> directorIDs;
}
