package BookMyShow.controllers;

import BookMyShow.Exceptions.ShowExistException;
import BookMyShow.RequestDtos.AddShowRequest;
import BookMyShow.RequestDtos.AddShowSeatRequest;
import BookMyShow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("show")
public class Showcontroller {

    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    public String addShow(@RequestBody AddShowRequest addShowRequest)
    {
        try {
            return showService.addShow(addShowRequest);
        }
        catch (ShowExistException e)
        {
            return e.getMessage();
        }
    }

    @PostMapping("/createShowSeats")
    public String enableShowSeats(@RequestBody AddShowSeatRequest addShowSeatRequest)
    {
        return showService.createShowSeats(addShowSeatRequest);
    }
}
