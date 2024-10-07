package BookMyShow.exceptions;

public class ShowExistException extends RuntimeException {
    public ShowExistException (String msg){
        super((msg));
    }
}
