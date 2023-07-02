package ru.homework.hometask07.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.homework.hometask07.dao.entity.FilmEntity;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {
/*    @Query(nativeQuery = true,
            value = """
                       select distinct b.*
                       from films b
                                left join films_directors ba on b.id = ba.film_id
                                left join directors a on a.id = ba.director_id
                       where b.title ilike '%' || coalesce(:title, '%')  || '%'
                         and cast(b.genre as char) like coalesce(:genre, '%')
                         and coalesce(a.fio, '%') ilike '%' ||  coalesce(:fio, '%')  || '%'
                         and b.is_deleted = false
                    """)
    Page<FilmEntity> searchFilms(@Param(value = "title") String filmTitle,
                           @Param(value = "genre") String genre,
                           @Param(value = "fio") String directorFIO,
                           Pageable pageRequest);*/
}
