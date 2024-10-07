package BookMyShow.services;

import BookMyShow.enums.SeatType;
import BookMyShow.exceptions.TheaterExistException;
import BookMyShow.requestDtos.AddTheaterRequest;
import BookMyShow.entities.Theater;
import BookMyShow.entities.TheaterSeat;
import BookMyShow.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public  String addTheater(AddTheaterRequest addTheaterRequest) throws TheaterExistException
    {
        //1. Create the Theater Entity
        Theater existingTheater=theaterRepository.getByName(addTheaterRequest.getName());
        if(existingTheater!=null)
        {
            throw new TheaterExistException("Theater named "+existingTheater.getName()+" already Exist");
        }

        Theater existingTheater2=theaterRepository.getByAddress(addTheaterRequest.getAddress());
        if(existingTheater2!=null)
        {
            throw new TheaterExistException(("Theater named "+existingTheater2.getName()+" already exist in this address"));
        }
        Theater theater=Theater.builder()   .name(addTheaterRequest.getName())
                .address(addTheaterRequest.getAddress())
                .city(addTheaterRequest.getCity())
                .build();
        //Create theater seat entity
        createTheaterSeats(theater,addTheaterRequest);

        return "Theater "+addTheaterRequest.getName()+ " and its Seats added Successfully !!";
    }

    public void createTheaterSeats(Theater theater, AddTheaterRequest addTheaterRequest)
    {
        int noOfClassicSeats= addTheaterRequest.getNoOfClassicSeats();
        int noOfPremiumSeats= addTheaterRequest.getNoOfPremiumSeats();
        int noOfSeatsPerRow=addTheaterRequest.getNoOfSeatsPerRow();

        //Create the Primary Seat Entities
        List<TheaterSeat> theaterSeatList=new ArrayList<>();

        int row=0;
        char ch='A';

        for(int i=1;i<=noOfClassicSeats;i++)
        {
            if(i%noOfSeatsPerRow==1)
            {
                row++;
                ch='A';
            }
            String seatNo=row+""+ch;
            ch++;

            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.CLASSIC)
                    .theater(theater)
                    .build();
            theaterSeatList.add(theaterSeat);
        }

        //Similar numbering will do for the Premium Seats :
        ch = 'A';
        for(int i=1;i<=noOfPremiumSeats;i++) {

            if (i % noOfSeatsPerRow == 1) {
                row++;
                ch = 'A';
            }
            String seatNo = row + "" + ch;
            ch = (char) (ch + 1);

            TheaterSeat theaterSeat = TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.PREMIUM)
                    .theater(theater) //Setting the FK also
                    .build();

            theaterSeatList.add(theaterSeat);
        }

        //This is done for bidirectional mapping
        theater.setTheaterSeatList(theaterSeatList);
        theaterRepository.save(theater);
    }

    //updateTheater - Put Mapping

}
