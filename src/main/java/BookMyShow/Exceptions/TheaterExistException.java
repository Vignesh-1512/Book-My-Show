package BookMyShow.Exceptions;

public class TheaterExistException extends RuntimeException{
    public TheaterExistException (String msg)
    {
        super(msg);
    }
}
