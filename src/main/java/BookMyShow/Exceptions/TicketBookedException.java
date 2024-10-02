package BookMyShow.Exceptions;

public class TicketBookedException extends  RuntimeException{
    public TicketBookedException (String msg)
    {
        super(msg);
    }
}
