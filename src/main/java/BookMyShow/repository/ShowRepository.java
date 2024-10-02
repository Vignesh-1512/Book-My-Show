package BookMyShow.repository;

import BookMyShow.entities.Movie;
import BookMyShow.entities.Show;
import BookMyShow.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {
    Show findShowByShowDateAndShowTimeAndMovieAndTheater(LocalDate showDate, LocalTime showTime, Movie movie, Theater theater);
    Show findShowByShowDateAndShowTimeAndTheater(LocalDate showDate, LocalTime showTime, Theater theater);
}
