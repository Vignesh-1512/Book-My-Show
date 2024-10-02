package BookMyShow.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    private String movieName;

    private Integer totalPrice;

    private String bookedSeats;

    private LocalDate showDate;

    private LocalTime showTime;

    private String theaterAddress;

    @JoinColumn
    @ManyToOne
    private Show show;

    @JoinColumn
    @ManyToOne
    private User user;
}
