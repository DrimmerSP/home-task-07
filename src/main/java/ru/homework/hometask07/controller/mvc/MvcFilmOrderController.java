package ru.homework.hometask07.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.controller.dto.OrderDto;
import ru.homework.hometask07.mapper.FilmMapper;
import ru.homework.hometask07.mapper.OrderMapper;
import ru.homework.hometask07.service.FilmService;
import ru.homework.hometask07.service.OrderService;
import ru.homework.hometask07.service.userdetails.CustomUserDetails;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rent")
public class MvcFilmOrderController {
    private final OrderService orderService;
    private final FilmService filmService;
    private final FilmMapper filmMapper;
    private final OrderMapper orderMapper;

//    public MvcFilmOrderController(OrderService orderService, FilmService filmService, OrderMapper orderMapper) {
//        this.orderService = orderService;
//        this.filmService = filmService;
//        this.orderMapper = orderMapper;
//    }

    @GetMapping("/film/{filmId}")
    public String rentFilm(@PathVariable Long filmId,
                           Model model) {
        model.addAttribute("film", filmMapper.toDto(filmService.getOne(filmId)));
        return "userFilms/buyFilm";
    }

    @PostMapping("/film")
    public String rentFilm(@ModelAttribute("orderInfo") OrderDto orderDto) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderDto.setUserID(customUserDetails.getUserId());
        orderService.rentFilm(orderMapper.toEntity(orderDto));
        return "redirect:/rent/user-films/%s".formatted(customUserDetails.getUserId());
    }

    @GetMapping("/user-films/{id}")
    public String userFilms(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "size", defaultValue = "5") int pageSize,
                            @PathVariable Long id,
                            Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<OrderDto> orders = orderService.listAll(pageRequest);
        model.addAttribute("rentFilms", orders);
        return "userFilms/viewAllUserFilms";
    }
}
