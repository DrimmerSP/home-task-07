package ru.homework.hometask07.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.hometask07.controller.dto.FilmDto;
import ru.homework.hometask07.dao.DirectorRepository;
import ru.homework.hometask07.dao.entity.FilmEntity;
import ru.homework.hometask07.dao.entity.GenericEntity;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FilmMapper extends GenericMapper<FilmEntity, FilmDto>{
    private final DirectorRepository directorRepository;

    @Override
    public FilmEntity toEntity(FilmDto dto) {
        FilmEntity result = new FilmEntity();
        result.setId(dto.getId());
        result.setTitle(dto.getTitle());
        result.setPremierDate(dto.getPremierDate());
        result.setCountry(dto.getCountry());
        result.setGenre(dto.getGenre());
        result.setDescription(dto.getDescription());
        result.setDirectors(dto.getDirectorIDs().stream()
                .map(id -> directorRepository.findById(id).orElse(null)).collect(Collectors.toList()));
        result.setCreatedWhen(dto.getCreatedWhen());
        result.setCreatedBy(dto.getCreatedBy());
        result.setDeletedWhen(dto.getDeletedWhen());
        result.setDeletedBy(dto.getDeletedBy());
        result.setDeleted(dto.isDeleted());
        return null;
    }

    @Override
    public FilmDto toDto(FilmEntity entity) {
        FilmDto result = new FilmDto();
        result.setId(entity.getId());
        result.setTitle(entity.getTitle());
        result.setPremierDate(entity.getPremierDate());
        result.setCountry(entity.getCountry());
        result.setGenre(entity.getGenre());
        result.setDescription(entity.getDescription());
        result.setDirectorIDs(entity.getDirectors().stream().map(GenericEntity::getId).collect(Collectors.toList()));
        result.setCreatedWhen(entity.getCreatedWhen());
        result.setCreatedBy(entity.getCreatedBy());
        result.setDeletedWhen(entity.getDeletedWhen());
        result.setDeletedBy(entity.getDeletedBy());
        result.setDeleted(entity.isDeleted());
        return result;
    }
}
