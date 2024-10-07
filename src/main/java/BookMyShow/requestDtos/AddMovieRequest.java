package BookMyShow.requestDtos;

import BookMyShow.enums.Genre;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AddMovieRequest {
    private  String movieName;

    private double ratings;

    private Genre genre;

    private LocalDate releaseDate;
}
