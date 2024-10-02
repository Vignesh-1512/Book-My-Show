package BookMyShow.Exceptions;

public class UserExistException extends  RuntimeException{
    public UserExistException (String msg)
    {
        super(msg);
    }
}
