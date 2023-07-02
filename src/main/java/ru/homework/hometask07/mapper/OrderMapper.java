package ru.homework.hometask07.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.hometask07.controller.dto.OrderDto;
import ru.homework.hometask07.dao.FilmRepository;
import ru.homework.hometask07.dao.UserRepository;
import ru.homework.hometask07.dao.entity.GenericEntity;
import ru.homework.hometask07.dao.entity.OrderEntity;

import java.time.Duration;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper extends GenericMapper<OrderEntity, OrderDto>{
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    @Override
    public OrderEntity toEntity(OrderDto dto) {
        OrderEntity result = new OrderEntity();
        result.setId(dto.getId());
        result.setRentFrom(dto.getRentFrom());
        result.setRentTo(dto.getRentFrom().plus(dto.getRentPeriod()));
        result.setIsPurchase(dto.getPurchase());
        result.setUser(userRepository.findById(dto.getUserID()).orElse(null));
        result.setFilms(dto.getFilmIDs().stream()
                .map(id -> filmRepository.findById(id).orElse(null)).collect(Collectors.toList()));
        result.setCreatedWhen(dto.getCreatedWhen());
        result.setCreatedBy(dto.getCreatedBy());
        result.setDeletedWhen(dto.getDeletedWhen());
        result.setDeletedBy(dto.getDeletedBy());
        result.setDeleted(dto.isDeleted());
        return null;
    }

    @Override
    public OrderDto toDTO(OrderEntity entity) {
        OrderDto result = new OrderDto();
        result.setId(entity.getId());
        result.setRentFrom(entity.getRentFrom());
        result.setRentPeriod(Duration.between(entity.getRentFrom(), entity.getRentTo()));
        result.setPurchase(entity.getIsPurchase());
        result.setUserID(entity.getUser().getId());
        result.setFilmIDs(entity.getFilms().stream()
                .map(GenericEntity::getId).collect(Collectors.toList()));
        result.setCreatedWhen(entity.getCreatedWhen());
        result.setCreatedBy(entity.getCreatedBy());
        result.setDeletedWhen(entity.getDeletedWhen());
        result.setDeletedBy(entity.getDeletedBy());
        result.setDeleted(entity.isDeleted());
        return null;
    }
}
