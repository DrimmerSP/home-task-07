package ru.homework.hometask07.controller.dto;

import java.util.List;

public record DirectorDto(
        Integer id,
        String directorFIO,
        Integer position,
        List<Integer> filmIDs
) {
}
