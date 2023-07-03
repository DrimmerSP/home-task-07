package ru.homework.hometask07.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.homework.hometask07.model.Genre;

@Getter
@Setter
@ToString
public class FilmSearchDto {
    private String filmTitle;
    private String DirectorFIO;
    private Genre genre;
}
