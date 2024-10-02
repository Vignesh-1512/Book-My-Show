package BookMyShow.controllers;

import BookMyShow.Exceptions.MovieExistException;
import BookMyShow.RequestDtos.AddMovieRequest;
import BookMyShow.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody AddMovieRequest addMovieRequest)
    {
        try {
            return movieService.addMovie(addMovieRequest);
        }
        catch (MovieExistException e)
        {
            return e.getMessage();
        }
    }
}
