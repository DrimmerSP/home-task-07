package ru.homework.hometask07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.dao.DirectorRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;

    public List<DirectorEntity> getAllDirectors() {
        return directorRepository.findAll();
    }

    public DirectorEntity getDirectorByID(Integer id) {
        return directorRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Режиссёр ID: %s не найден.".formatted(id)));
    }

    public DirectorEntity createDirector(DirectorEntity body) {
        return directorRepository.save(body);
    }

    public DirectorEntity updateDirector(Integer id, DirectorEntity body) {
        body.setId(id);
        return directorRepository.save(body);
    }

    public void deleteDirectorByID(Integer id) {
        directorRepository.deleteById(id);
    }
}
