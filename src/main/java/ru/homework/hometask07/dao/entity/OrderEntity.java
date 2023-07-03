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
@SequenceGenerator(name = "default_generator", sequenceName = "users_seq", allocationSize = 1)
public class OrderEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_ORDER_USER"))
    private UserEntity user;

    @OneToMany
    @JoinTable(
            name = "order_films",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private List<FilmEntity> films;

    @Column(name = "rent_from", nullable = false)
    private LocalDate rentFrom;

    @Column(name = "rent_to", nullable = false)
    private LocalDate rentTo;

    @Column(name = "is_purchase")
    private Boolean isPurchase;

    @Column(name = "is_return")
    private Boolean isReturned;
}
