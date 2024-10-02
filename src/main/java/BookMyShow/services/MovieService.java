package BookMyShow.services;

import BookMyShow.Exceptions.MovieExistException;
import BookMyShow.RequestDtos.AddMovieRequest;
import BookMyShow.Transformers.MovieTransformer;
import BookMyShow.entities.Movie;
import BookMyShow.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
