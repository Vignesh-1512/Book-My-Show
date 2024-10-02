package BookMyShow.services;

import BookMyShow.Exceptions.TicketBookedException;
import BookMyShow.entities.Movie;
import BookMyShow.entities.Show;
import BookMyShow.entities.ShowSeat;
import BookMyShow.entities.Theater;
import BookMyShow.entities.Ticket;
import BookMyShow.entities.User;
import BookMyShow.repository.MovieRepository;
import BookMyShow.repository.ShowRepository;
import BookMyShow.repository.TheaterRepository;
import BookMyShow.repository.TicketRepository;
import BookMyShow.repository.UserRepository;
import BookMyShow.RequestDtos.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServices {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public String bookTicket (BookTicketRequest bookTicketRequest) throws TicketBookedException
    {

        Show show = findRightShow(bookTicketRequest);
        //My steps are :
        List<ShowSeat> showSeatList = show.getShowSeatList();
        //Whatever are the requested seats : mark them as not available in show seats
        List<String> unavailableSeats = new ArrayList<>();
        int totalPrice = 0;

        // Check if all requested seats are available
        for (String requestedSeatNo : bookTicketRequest.getRequestedSeatNos()) {
            ShowSeat requestedSeat = showSeatList.stream()
                    .filter(seat -> seat.getSeatNo().equals(requestedSeatNo))
                    .findFirst()
                    .orElse(null);

            // Check if the seat exists and is available
            if (requestedSeat == null || !requestedSeat.isAvailable()) {
                unavailableSeats.add(requestedSeatNo);  // Add to the list of unavailable seats
            } else {
                // Mark seat as not available and add the price
                requestedSeat.setAvailable(false);
                totalPrice += requestedSeat.getCost();
            }
        }

        // If there are any unavailable seats, return an error message listing them
        if (!unavailableSeats.isEmpty()) {
            throw new TicketBookedException("The following seats are already booked or do not exist: " + String.join(", ", unavailableSeats));
        }

//        for(ShowSeat showSeat:showSeatList) {
//
//            if(bookTicketRequest.getRequestedSeatNos().contains(showSeat.getSeatNo()) && showSeat.isAvailable()) {
//
//                // Mark the seat as not available and add the cost to the total
//                showSeat.setAvailable(false);
//                totalPrice = totalPrice + showSeat.getCost();
//            }
//        }

        User user = userRepository.findById(bookTicketRequest.getUserId()).get();

        Ticket ticket = Ticket.builder()
                .movieName(show.getMovie().getMovieName())
                .theaterAddress(show.getTheater().getAddress())
                .showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .bookedSeats(bookTicketRequest.getRequestedSeatNos().toString())
                .user(user)
                .show(show)
                .totalPrice(totalPrice)
                .build();

        show.getTicketList().add(ticket);
        user.getTicketList().add(ticket);


        ticketRepository.save(ticket);


        return "Ticket has been Successfullly booked";

        //Calculate total Price

        //We also need to add it to list of booked tickets against user





    }

    private Show findRightShow(BookTicketRequest bookTicketRequest){

        Movie movie = movieRepository.findMovieByMovieName(bookTicketRequest.getMovieName());
        Theater theater = theaterRepository.findById(bookTicketRequest.getTheaterId()).get();

        Show show = showRepository.findShowByShowDateAndShowTimeAndMovieAndTheater(bookTicketRequest.getShowDate()
                ,bookTicketRequest.getShowTime(),
                movie,theater);


        return show;
    }

}