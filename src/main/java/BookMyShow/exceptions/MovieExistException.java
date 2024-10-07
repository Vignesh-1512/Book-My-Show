package BookMyShow.exceptions;

public class MovieExistException extends RuntimeException{
    public MovieExistException (String msg)
    {
        super(msg);
    }
}
