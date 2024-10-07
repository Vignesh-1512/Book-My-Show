package BookMyShow.requestDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddShowSeatRequest {
    private Integer showId;
    private Integer priceOfClassicSeats;
    private Integer priceOfPremiumSeats;
}
