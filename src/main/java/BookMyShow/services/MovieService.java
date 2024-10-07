package BookMyShow.services;

import BookMyShow.enums.Genre;
import BookMyShow.exceptions.MovieExistException;
import BookMyShow.requestDtos.AddMovieRequest;
import BookMyShow.entities.Movie;
import BookMyShow.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(AddMovieRequest addMovieRequest) throws MovieExistException
    {
        Movie existingMovie= movieRepository.findMovieByMovieName(addMovieRequest.getMovieName());
        if(existingMovie!=null)
        {
            throw new MovieExistException("Movie named "+existingMovie.getMovieName()+" already exist");
        }
        Movie movie=new Movie();
        movie.setMovieName(addMovieRequest.getMovieName());
        movie.setRatings(addMovieRequest.getRatings());
        movie.setGenre(addMovieRequest.getGenre());
        movie.setReleaseDate(addMovieRequest.getReleaseDate());
        movieRepository.save(movie);

        return "Movie "+addMovieRequest.getMovieName()+" has been added Succesfully";
    }

    //getAllMoviesByGenre
    public List<AddMovieRequest> getAllMoviesByGenre(Genre genre) {
        List<Movie> movies = movieRepository.findByGenre(genre);
        return movies.stream()
                .map(movie -> new AddMovieRequest(
                        movie.getMovieName(),
                        movie.getRatings(),
                        movie.getGenre(),
                        movie.getReleaseDate()
                ))
                .collect(Collectors.toList());
    }

    //getMovieAboveRating
    public List<AddMovieRequest> getMoviesAboveRating(double rating) {
        List<Movie> movies = movieRepository.findByRatingsGreaterThan(rating);
        return movies.stream()
                .map(movie -> new AddMovieRequest(
                        movie.getMovieName(),
                        movie.getRatings(),
                        movie.getGenre(),
                        movie.getReleaseDate()
                ))
                .collect(Collectors.toList());
    }
}
