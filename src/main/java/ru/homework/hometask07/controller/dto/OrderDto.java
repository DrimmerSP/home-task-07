package ru.homework.hometask07.controller.dto;

import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto extends GenericDto {
    private Long userID;
    private List<Long> filmIDs;
    private LocalDate rentFrom;
    private Duration rentPeriod;
    private Boolean purchase;
    private Boolean returned;
}
