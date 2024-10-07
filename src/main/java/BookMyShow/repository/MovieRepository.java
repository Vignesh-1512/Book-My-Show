package BookMyShow.repository;


import BookMyShow.entities.Movie;
import BookMyShow.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findMovieByMovieName(String movieName);

    List<Movie> findByGenre(Genre genre);

    List<Movie> findByRatingsGreaterThan(double ratings);
}
