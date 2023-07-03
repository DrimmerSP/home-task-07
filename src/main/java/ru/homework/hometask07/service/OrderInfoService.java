package ru.homework.hometask07.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.controller.dto.FilmDto;

import java.time.LocalDateTime;

@Service
public class OrderInfoService {
 /*   private final FilmService filmService;

    protected FilmRentInfoService(FilmRentInfoRepository filmRentInfoRepository,
                                  FilmRentInfoMapper filmRentInfoMapper, FilmService filmService) {
        super(filmRentInfoRepository, filmRentInfoMapper);
        this.filmService = filmService;
    }

    public BookRentInfoDTO rentFilm(final FilmRentInfoDTO rentInfoDto) {
        FilmDto filmDto = filmService.getOne(rentInfoDto.getFilmId());
        filmDto.setAmount(filmDto.getAmount() - 1);
        filmService.update(filmDto);

        long rentPeriod = rentInfoDto.getRentPeriod() != null ? rentInfoDto.getRentPeriod() : 14L;
        rentInfoDto.setRentDate(LocalDateTime.now());
        rentInfoDto.setReturned(false);
        rentInfoDto.setRentPeriod((int) rentPeriod);
        rentInfoDto.setReturnDate(LocalDateTime.now().plusDays(rentPeriod));
        rentInfoDto.setCreatedWhen(LocalDateTime.now());
        rentInfoDto.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        return mapper.toDTO(repository.save(mapper.toEntity(rentInfoDto)));
    }*/


}
