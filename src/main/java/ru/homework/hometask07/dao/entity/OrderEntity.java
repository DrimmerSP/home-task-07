package ru.homework.hometask07.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    @Column(name = "user_id")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_ORDER_USER"))
    private UserEntity user;

    //    @Column(name = "film_id")
    @OneToMany
//    @JoinColumn(name = "filmId", nullable = false,   //film-filmId
//            foreignKey = @ForeignKey(name = "FK_ORDER_FILM"))
    @JoinTable(
            name = "order_films",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private List<FilmEntity> film;

    @Column(name = "rent_from", nullable = false)
    private LocalDate rentFrom;

    @Column(name = "rent_to", nullable = false)
    private LocalDate rentTo;

    @Column(name = "purchase")
    private Boolean purchase;
}
