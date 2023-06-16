package ru.homework.hometask07.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.homework.hometask07.dao.entity.FilmEntity;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {
}
