package BookMyShow.controllers;

import BookMyShow.exceptions.TheaterExistException;
import BookMyShow.requestDtos.AddTheaterRequest;
import BookMyShow.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/addTheater")
    public String addTheater(@RequestBody AddTheaterRequest addTheaterRequest)
    {
        try {
            return theaterService.addTheater(addTheaterRequest);
        }
        catch (TheaterExistException e)
        {
            return e.getMessage();
        }
        //return "Theater Added Successfully !!";
    }
}
