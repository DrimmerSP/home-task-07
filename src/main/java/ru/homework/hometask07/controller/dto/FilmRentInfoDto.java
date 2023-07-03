package ru.homework.hometask07.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmRentInfoDto {
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;
    private Boolean returned;
    private Integer rentPeriod;
    private Long filmId;
    private Long userId;
    private FilmDto filmDto;
}
