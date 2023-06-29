package ru.homework.hometask07.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.homework.hometask07.model.Country;
import ru.homework.hometask07.model.Genre;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "films")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmEntity {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;

    @Column(name = "premier_date")
    private LocalDate premierDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    private Country country;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @ManyToMany
    @JoinTable(
            name = "film_directors",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private List<DirectorEntity> directors;
}
