package com.example.cinema.dao.repository;

import com.example.cinema.dao.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {
    Optional<SeatEntity> findByRowOfSeatAndColumnOfSeatAndHallId(Integer rowOfSeat,
                                                                 Integer columnOfSeat,
                                                                 Long hallId);


}
