package BookMyShow.controllers;

import BookMyShow.exceptions.ShowExistException;
import BookMyShow.exceptions.ShowNotFoundException;
import BookMyShow.requestDtos.AddShowRequest;
import BookMyShow.requestDtos.AddShowSeatRequest;
import BookMyShow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/updateCost")
    public String updateShowCost(@RequestBody AddShowSeatRequest addShowSeatRequest) {
        try {
            return showService.updateCostOfShowById(addShowSeatRequest);
        }
        catch (ShowNotFoundException e)
        {
            return e.getMessage();
        }
    }
}
