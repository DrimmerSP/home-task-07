package ru.homework.hometask07.controller.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDto extends GenericDto {
    private String directorFIO;
    private Integer position;
    private List<Long> filmIDs;


}
