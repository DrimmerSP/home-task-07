package ru.homework.hometask07.dao.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "films")
public class FilmEntity {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "premier_date")
    private LocalDate premierDate;

    @Column(name = "country")
    private String country;

    @Column(name = "genre")
    private String genre;

    @ManyToMany
    @JoinTable(
            name = "film_directors",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private List<DirectorEntity> directors;
}
