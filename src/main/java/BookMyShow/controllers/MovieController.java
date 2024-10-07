package BookMyShow.controllers;

import BookMyShow.entities.Movie;
import BookMyShow.enums.Genre;
import BookMyShow.exceptions.MovieExistException;
import BookMyShow.requestDtos.AddMovieRequest;
import BookMyShow.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody AddMovieRequest addMovieRequest) {
        try {
            return movieService.addMovie(addMovieRequest);
        } catch (MovieExistException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/genre/{genre}")
    public List<AddMovieRequest> getMoviesByGenre(@PathVariable Genre genre) {
        return movieService.getAllMoviesByGenre(genre);
    }

    @GetMapping("/ratings-above/{rating}")
    public List<AddMovieRequest> getMoviesAboveRating(@PathVariable double rating) {
        return movieService.getMoviesAboveRating(rating);
    }
}
