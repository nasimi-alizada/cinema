package com.example.cinema.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@Entity
@Table(name = "halls")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class HallEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private Integer maxSeats;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cinema_id", nullable = false)
    @ToString.Exclude
    private CinemaEntity cinema;

    @OneToMany(
            mappedBy = "hall",
            fetch = LAZY, cascade = ALL
    )
    @ToString.Exclude
    private List<SeatEntity> seats;

    @OneToMany(
            mappedBy = "hall",
            cascade = ALL
    )
    @ToString.Exclude
    private List<SessionEntity> sessions;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        HallEntity that = (HallEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
