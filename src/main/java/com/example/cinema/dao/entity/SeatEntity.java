package com.example.cinema.dao.entity;

import com.example.cinema.model.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@Entity
@Table(name = "seats")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Integer rowOfSeat;

    private Integer columnOfSeat;

    @Enumerated(STRING)
    private SeatStatus seatStatus;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hall_id", nullable = false)
    @ToString.Exclude
    private HallEntity hall;

    @OneToOne(mappedBy = "seat",
            fetch = LAZY,
            cascade = ALL)
    @ToString.Exclude
    private TicketEntity ticket;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SeatEntity that = (SeatEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
