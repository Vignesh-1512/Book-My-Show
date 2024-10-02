package BookMyShow.controllers;

import BookMyShow.Exceptions.TicketBookedException;
import BookMyShow.RequestDtos.BookTicketRequest;
import BookMyShow.services.TicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketServices ticketServices;

    @PostMapping("/bookTicket")
    private String bookTicket(@RequestBody BookTicketRequest bookTicketRequest)
    {
        try {
            return ticketServices.bookTicket(bookTicketRequest);
        }
        catch (TicketBookedException e)
        {
            return e.getMessage();
        }
    }
}
