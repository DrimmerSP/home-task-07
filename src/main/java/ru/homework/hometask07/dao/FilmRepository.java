package ru.homework.hometask07.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.homework.hometask07.dao.entity.FilmEntity;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

    @Query(nativeQuery = true,
            value = """
                       SELECT DISTINCT f.*
                       FROM films f 
                                LEFT JOIN film_directors fd ON f.id = fd.film_id
                                LEFT JOIN directors d ON d.id = fd.director_id
                       WHERE f.title ILIKE '%' || COALESCE(:title, '%')  || '%'
                         AND CAST(f.genre AS CHAR) LIKE COALESCE(:genre, '%')
                         AND COALESCE(d.director_fio, '%') ILIKE '%' ||  COALESCE(:directorFio, '%')  || '%'
                         AND f.is_deleted = FALSE
                    """)
    Page<FilmEntity> searchFilms(String title, String genre, String directorFio, Pageable pageRequest);
}
