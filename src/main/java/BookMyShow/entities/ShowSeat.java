package BookMyShow.entities;

import BookMyShow.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="show_seat")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showSeatId;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Integer cost;

    private  boolean isAvailable;

    private boolean isFoodAttached;

    @JoinColumn
    @ManyToOne
    private Show show;


}
