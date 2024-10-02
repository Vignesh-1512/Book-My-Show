package BookMyShow.entities;

import BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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
