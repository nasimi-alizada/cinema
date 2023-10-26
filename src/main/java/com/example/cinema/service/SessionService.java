package com.example.cinema.service;

import com.example.cinema.dao.entity.SessionEntity;
import com.example.cinema.dao.repository.HallRepository;
import com.example.cinema.dao.repository.MovieRepository;
import com.example.cinema.dao.repository.SessionRepository;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.mapper.SessionMapper;
import com.example.cinema.model.request.SessionRequest;
import com.example.cinema.model.response.SessionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cinema.mapper.SessionMapper.*;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;

    public List<SessionResponse> getAllSessions() {
        return sessionRepository.findAll().stream()
                .map(SessionMapper::buildSessionResponse).collect(Collectors.toList());
    }

    public SessionResponse getSessionById(Long id) {
        var sessionEntity = fetchSessionIfExist(id);
        return buildSessionResponse(sessionEntity);
    }

    @Transactional
    public void addSession(SessionRequest sessionRequest, Long hallId, Long movieId) {
        var hallEntity = hallRepository.findById(hallId).orElseThrow(
                () -> new EntityNotFoundException("HALL_NOT_FOUND")
        );

        var movieEntity = movieRepository.findById(movieId).orElseThrow(
                () -> new EntityNotFoundException("MOVIE_NOT_FOUND")
        );
        var sessionEntity = buildSessionEntity(sessionRequest);
        sessionEntity.setMovie(movieEntity);
        sessionEntity.setHall(hallEntity);


        sessionRepository.save(sessionEntity);
    }


    @Transactional
    public void updateSession(Long id, SessionRequest sessionRequest,
                              Long hallId, Long movieId) {
        var sessionEntity = fetchSessionIfExist(id);
        var hallEntity = hallRepository.findById(hallId).orElseThrow(
                () -> new EntityNotFoundException("HALL_NOT_FOUND")
        );

        var movieEntity = movieRepository.findById(movieId).orElseThrow(
                () -> new EntityNotFoundException("MOVIE_NOT_FOUND")
        );

        buildUpdateSessionEntity(sessionRequest, sessionEntity);
        sessionEntity.setHall(hallEntity);
        sessionEntity.setMovie(movieEntity);

        sessionRepository.save(sessionEntity);
    }


    public void deleteSession(Long id) {
        fetchSessionIfExist(id);
        sessionRepository.deleteById(id);
    }

    private SessionEntity fetchSessionIfExist(Long id) {
        return sessionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("SESSION_NOT_FOUND")
        );

    }
}
