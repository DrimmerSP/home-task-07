package ru.homework.hometask07.dao.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "directors")
public class DirectorEntity {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "director_fio")
    private String directorFIO;

    @Column(name = "position")
    private Integer position;

    @ManyToMany
    @JoinTable(
            name = "film_directors",
            joinColumns = @JoinColumn(name = "director_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private List<FilmEntity> films;
}
