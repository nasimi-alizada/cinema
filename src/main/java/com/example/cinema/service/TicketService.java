package com.example.cinema.service;

import com.example.cinema.dao.entity.SeatEntity;
import com.example.cinema.dao.entity.SessionEntity;
import com.example.cinema.dao.entity.TicketEntity;
import com.example.cinema.dao.entity.UserEntity;
import com.example.cinema.dao.repository.SeatRepository;
import com.example.cinema.dao.repository.SessionRepository;
import com.example.cinema.dao.repository.TicketRepository;
import com.example.cinema.dao.repository.UserRepository;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.exception.NotEnoughBalanceException;
import com.example.cinema.exception.OccupiedSeatException;
import com.example.cinema.model.enums.SeatStatus;
import com.example.cinema.model.request.SeatRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final SessionRepository sessionRepository;

    @Transactional
    public void getTicket(List<SeatRequest> seatRequestList,
                          Long userId, Long sessionId) {

        var userEntity = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("USER_NOT_FOUND"));

        var sessionEntity = sessionRepository.findById(sessionId).orElseThrow(
                () -> new EntityNotFoundException("SESSION_NOT_FOUND"));

        buyTicket(seatRequestList, sessionEntity, userEntity);

        var ticketPrice = sessionEntity.getPrice().multiply(BigDecimal.valueOf(seatRequestList.size()));

        if (userEntity.getBalance().compareTo(ticketPrice) < 0) {
            throw new NotEnoughBalanceException("Not Enough Balance");
        }
        userEntity.setBalance(userEntity.getBalance().subtract(ticketPrice));
        userRepository.save(userEntity);
        log.info("Ticket is got successfully");

    }

    private void buyTicket(List<SeatRequest> seatRequests,
                           SessionEntity sessionEntity, UserEntity userEntity) {
        seatRequests.forEach(seatRequest -> {
            var seatEntity = getSeat(sessionEntity, seatRequest);

            if (seatEntity.getSeatStatus().equals(SeatStatus.OCCUPIED)) {
                throw new OccupiedSeatException("SEAT IS OCCUPIED");
            }
            sellSeat(seatEntity);
            saveTicket(sessionEntity, userEntity, seatEntity);
        });
    }
    private SeatEntity getSeat(SessionEntity sessionEntity, SeatRequest seatRequest) {
        return seatRepository.findByRowOfSeatAndColumnOfSeatAndHallId(
                seatRequest.getRowOfSeat(),
                seatRequest.getColumnOfSeat(),
                sessionEntity.getHall().getId()
        ).orElseThrow(() -> new EntityNotFoundException("SEAT_NOT_FOUND"));
    }

    private void sellSeat(SeatEntity seatEntity) {
        seatEntity.setSeatStatus(SeatStatus.OCCUPIED);
        seatRepository.save(seatEntity);
        log.info("Seat sold successfully");
    }




    private void saveTicket(SessionEntity sessionEntity,
                            UserEntity userEntity, SeatEntity seatEntity) {
        var ticketEntity = TicketEntity.builder()
                .session(sessionEntity)
                .seat(seatEntity)
                .user(userEntity)
                .build();

        ticketRepository.save(ticketEntity);
        log.info("Ticket saved successfully");
    }
}
