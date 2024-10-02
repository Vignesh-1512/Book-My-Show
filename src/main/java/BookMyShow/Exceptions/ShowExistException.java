package BookMyShow.Exceptions;

public class ShowExistException extends RuntimeException {
    public ShowExistException (String msg){
        super((msg));
    }
}
