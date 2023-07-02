package ru.homework.hometask07.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.hometask07.dao.entity.GenericEntity;

public interface GenericRepository<T extends GenericEntity> extends JpaRepository<T, Long> {
}
