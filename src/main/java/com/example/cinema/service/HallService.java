package com.example.cinema.service;

import com.example.cinema.dao.repository.CinemaRepository;
import com.example.cinema.dao.repository.HallRepository;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.mapper.CinemaMapper;
import com.example.cinema.mapper.HallMapper;
import com.example.cinema.model.request.HallRequest;
import com.example.cinema.model.response.CinemaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cinema.mapper.CinemaMapper.buildCinemaResponse;
import static com.example.cinema.mapper.HallMapper.buildHallEntity;

@Service
@RequiredArgsConstructor
public class HallService {
    private final HallRepository hallRepository;

    public void saveHall(HallRequest hallRequest) {
      hallRepository.save(buildHallEntity(hallRequest));
    }


}