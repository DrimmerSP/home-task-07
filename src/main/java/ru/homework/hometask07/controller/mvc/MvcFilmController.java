package ru.homework.hometask07.controller.mvc;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.homework.hometask07.controller.dto.FilmDto;
import ru.homework.hometask07.mapper.FilmMapper;
import ru.homework.hometask07.service.FilmService;

import java.util.List;

@Controller
@Hidden
@RequestMapping("/films/view")
@Slf4j
@RequiredArgsConstructor
public class MvcFilmController {
    private final FilmService filmService;
    //    private final DirectorService directorService;
    private final FilmMapper filmMapper;

    @GetMapping("")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "title"));
        List<FilmDto> result = filmService.getAllFilm(pageRequest).stream().map(filmMapper::entityToDto).toList();
        model.addAttribute("films", result);

        return "films/viewAllFilms";

//        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "authorFIO"));
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        Page<AuthorDTO> result;
//        if (ADMIN.equalsIgnoreCase(userName)) {
//            result = authorService.listAll(pageRequest);
//        }
//        else {
//            result = authorService.listAllNotDeleted(pageRequest);
//        }
//        model.addAttribute("authors", result);
    }
}
