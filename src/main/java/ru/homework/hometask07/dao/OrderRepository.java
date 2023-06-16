package ru.homework.hometask07.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.homework.hometask07.dao.entity.FilmEntity;
import ru.homework.hometask07.dao.entity.OrderEntity;

import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query("select o.film from OrderEntity as o where o.user.id = :userId and o.purchase = true")
    List<FilmEntity> getFilmsInUseByUserId(Integer userId);


}
