package ru.homework.hometask07.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        Integer id,
        Integer userID,
        List<Integer> filmID,
        LocalDateTime rentDate,
        LocalDateTime rentPeriod,
        Boolean purchase
) {
}
