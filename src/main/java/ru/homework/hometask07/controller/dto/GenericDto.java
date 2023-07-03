package ru.homework.hometask07.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public abstract class GenericDto {
    private Long id;
    private String createdBy;
    private LocalDateTime createdWhen;
    private LocalDateTime deletedWhen;
    private String deletedBy;
    private boolean isDeleted;
}
