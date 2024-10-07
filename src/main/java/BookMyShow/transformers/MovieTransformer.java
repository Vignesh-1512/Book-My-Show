package BookMyShow.transformers;

import BookMyShow.requestDtos.AddMovieRequest;
import BookMyShow.entities.Movie;

public class MovieTransformer {

    public static Movie convertAddMovierequestToMovie(AddMovieRequest addMovieRequest)
    {
        Movie movie=Movie.builder()
                .movieName(addMovieRequest.getMovieName())
                .ratings(addMovieRequest.getRatings())
                .genre(addMovieRequest.getGenre())
                .releaseDate(addMovieRequest.getReleaseDate())
                .build();
        return movie;
    }
}
