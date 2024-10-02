package BookMyShow.services;

import BookMyShow.Enums.SeatType;
import BookMyShow.Exceptions.ShowExistException;
import BookMyShow.RequestDtos.AddShowRequest;
import BookMyShow.RequestDtos.AddShowSeatRequest;
import BookMyShow.entities.*;
import BookMyShow.repository.MovieRepository;
import BookMyShow.repository.ShowRepository;
import BookMyShow.repository.ShowSeatRepository;
import BookMyShow.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public String addShow(AddShowRequest addShowRequest) throws ShowExistException
    {

        //Show show= ShowTransformer.convertAddRequestToEntity(addShowRequest);
        Movie movie= movieRepository.findMovieByMovieName(addShowRequest.getMovieName());

        Optional<Theater> optionalTheater=theaterRepository.findById(addShowRequest.getTheaterId());
        if(!optionalTheater.isPresent())
        {
            throw new RuntimeException("Teater not found");
        }
        Theater theater=optionalTheater.get();
        Show existingShow=showRepository.findShowByShowDateAndShowTimeAndTheater(addShowRequest.getShowDate(),
                addShowRequest.getShowTime(),
                theater);
        if (existingShow!=null) {
            throw new ShowExistException("A show already exists at "+existingShow.getShowTime()+ " on"+existingShow.getShowDate()+" in this theater.");
        }
        Show show=new Show();
        show.setShowDate(addShowRequest.getShowDate());
        show.setShowTime(addShowRequest.getShowTime());
        show.setMovie(movie);
        show.setTheater(theater);

        theater.getShowList().add(show);
        movie.getShowList().add(show);

        show = showRepository.save(show);

        return "Show has been saved to the DB with showId "+show.getShowId();
    }

    public String createShowSeats(AddShowSeatRequest addShowSeatRequest)
    {
        Show show=showRepository.findById(addShowSeatRequest.getShowId()).get();
        Theater theater=show.getTheater();
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = new ArrayList<>();


        for(TheaterSeat theaterSeat:theaterSeatList) {

            ShowSeat showSeat = ShowSeat.builder()
                    .seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType())
                    .isAvailable(true)
                    .isFoodAttached(false)
                    .show(show)
                    .build();

            if(theaterSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setCost(addShowSeatRequest.getPriceOfClassicSeats());
            }
            else{
                showSeat.setCost(addShowSeatRequest.getPriceOfPremiumSeats());
            }

            showSeatList.add(showSeat);
        }

        show.setShowSeatList(showSeatList);

        //Either save parent or save child

        //child is alot of seats (you need to save that list)

        showRepository.save(show);
        return "The show seats have been added";

    }
}
