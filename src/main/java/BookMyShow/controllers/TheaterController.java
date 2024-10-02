package BookMyShow.controllers;

import BookMyShow.Exceptions.TheaterExistException;
import BookMyShow.RequestDtos.AddTheaterRequest;
import BookMyShow.repository.TheaterRepository;
import BookMyShow.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
