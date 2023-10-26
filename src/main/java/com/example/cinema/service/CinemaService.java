package com.example.cinema.service;

import com.example.cinema.dao.entity.CinemaEntity;
import com.example.cinema.dao.entity.HallEntity;
import com.example.cinema.dao.repository.CinemaRepository;
import com.example.cinema.dao.repository.HallRepository;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.mapper.CinemaMapper;
import com.example.cinema.mapper.HallMapper;
import com.example.cinema.model.request.CinemaRequest;
import com.example.cinema.model.response.CinemaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cinema.mapper.CinemaMapper.buildCinemaEntity;
import static com.example.cinema.mapper.CinemaMapper.buildCinemaResponse;

@Service
@RequiredArgsConstructor
public class CinemaService {
    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;

    public List<CinemaResponse> getAllCinemas() {
        return cinemaRepository.findAll().stream()
                .map(CinemaMapper::buildCinemaResponse).collect(Collectors.toList());
    }

    public CinemaResponse getCinemaById(Long id) {
        var cinemaEntity = cinemaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("CINEMA_NOT_FOUND")
        );
        return buildCinemaResponse(cinemaEntity);

    }

    public void saveCinema(CinemaRequest cinemaRequest) {
        CinemaEntity cinemaEntity = buildCinemaEntity(cinemaRequest);

        CinemaEntity savedCinema = cinemaRepository.save(cinemaEntity);
        List<HallEntity> halls = cinemaRequest.getHallsRequest()
                .stream()
                .map(HallMapper::buildHallEntity)
                .toList();

        halls.forEach(hallEntity -> hallEntity.setCinema(savedCinema));

        hallRepository.saveAll(halls);
    }
}