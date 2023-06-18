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
import ru.homework.hometask07.controller.dto.DirectorDto;
import ru.homework.hometask07.mapper.DirectorMapper;
import ru.homework.hometask07.service.DirectorService;

import java.util.List;

@Controller
@Hidden
@RequestMapping("/directors/view")
@Slf4j
@RequiredArgsConstructor
public class MvcDirectorsController {
    private final DirectorService directorService;
    //    private final FilmService filmService;
    private final DirectorMapper directorMapper;

    @GetMapping("")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model
    ) {

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "directorFIO"));
        List<DirectorDto> result = directorService.getAllDirectors(pageRequest).stream().map(directorMapper::entityToDto).toList();
        model.addAttribute("directors", result);

        return "directors/viewAllDirectors";

        //        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "authorFIO"));
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        Page<AuthorDTO> result;
//        if (ADMIN.equalsIgnoreCase(userName)) {
//            result = authorService.listAll(pageRequest);
//        } else {
//            result = authorService.listAllNotDeleted(pageRequest);
//        }
//        model.addAttribute("authors", result);*/
    }
}
