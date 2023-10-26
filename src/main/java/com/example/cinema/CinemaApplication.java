package com.example.cinema;

import com.example.cinema.model.request.*;
import com.example.cinema.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.cinema.model.enums.GenreType.*;
import static com.example.cinema.model.enums.SessionType.EVENING;
import static com.example.cinema.model.enums.SessionType.MORNING;

@SpringBootApplication
@RequiredArgsConstructor
public class CinemaApplication implements CommandLineRunner {

    private final UserService userService;
    private final CinemaService cinemaService;
    private final MovieService movieService;
    private final SessionService sessionService;
    private final SeatService seatService;

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        userService.saveUser(new UserRequest("Ali", "Alizada", BigDecimal.valueOf(100)));
        userService.saveUser(new UserRequest("Nasimi", "Alizada", BigDecimal.valueOf(100)));

        var dolby = List.of(new HallRequest("Dolby Atmos", 30));
        var luxe = List.of(new HallRequest("Cinéma de Luxe", 30));

        cinemaService.saveCinema(new CinemaRequest("ParkCinema", dolby));

        cinemaService.saveCinema(new CinemaRequest("ParkCinema", luxe));

        movieService.saveMovie(new MovieRequest("Oppenheimer", 180, "Christopher Nolan",
                "written and directed by Christopher Nolan.", THRILLER));
        movieService.saveMovie(new MovieRequest("Tenet", 151, "Christopher Nolan",
                "written and directed by Christopher Nolan.", SCIENCE_FICTION));

        sessionService.addSession(new SessionRequest(LocalDateTime.now(), LocalDateTime.now(), MORNING, BigDecimal.TEN), 1L, 1L);
        sessionService.addSession(new SessionRequest(LocalDateTime.now(), LocalDateTime.now(), EVENING, BigDecimal.valueOf(15L)), 2L, 2L);


        for (int row = 0; row < 11; row++) {
            for (int column = 0; column < 21; column++) {
                seatService.saveSeat(new SeatRequest(row, column), 1L);
            }
        }

    }
}
