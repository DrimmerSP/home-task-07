package ru.homework.hometask07.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.hometask07.controller.dto.OrderDto;
import ru.homework.hometask07.dao.FilmRepository;
import ru.homework.hometask07.dao.UserRepository;
import ru.homework.hometask07.dao.entity.FilmEntity;
import ru.homework.hometask07.dao.entity.OrderEntity;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    public OrderDto entityToDto(OrderEntity entity) {
        return new OrderDto(
                entity.getId(),
                entity.getUser().getId(),
                entity.getFilm().stream().map(FilmEntity::getId).toList(),
                entity.getRentDate(),
                entity.getRentPeriod(),
                entity.getPurchase()
        );
    }

    public OrderEntity dtoToEntity(OrderDto dto) {
        return OrderEntity.builder()
                .id(dto.id())
                .user(userRepository.findById(dto.userID()).orElse(null))
                .film(dto.filmID().stream()
                        .map(id -> filmRepository.findById(id).orElse(null))
                        .toList())
                .rentDate(dto.rentDate())
                .rentPeriod(dto.rentPeriod())
                .purchase(dto.purchase())
                .build();
    }
}
