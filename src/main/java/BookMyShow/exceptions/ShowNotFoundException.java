package BookMyShow.exceptions;

public class ShowNotFoundException extends RuntimeException {
    public ShowNotFoundException(String msg)
    {
        super(msg);
    }
}
