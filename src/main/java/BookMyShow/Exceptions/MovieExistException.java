package BookMyShow.Exceptions;

public class MovieExistException extends RuntimeException{
    public MovieExistException (String msg)
    {
        super(msg);
    }
}
