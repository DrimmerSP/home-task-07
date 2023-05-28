package ru.homework.hometask07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import ru.homework.hometask07.dao.DirectorRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;
import ru.homework.hometask07.dao.entity.FilmEntity;

@RestController
@Tag(name = "Продюссер", description = "Автор или авторы фильма.")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorRepository directorRepository;




}
