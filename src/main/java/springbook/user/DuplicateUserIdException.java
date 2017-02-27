package springbook.user;

/**
 * Created by lse0101 on 09/02/2017.
 */
public class DuplicateUserIdException extends RuntimeException{
    public DuplicateUserIdException(Throwable cause) {
        super(cause);
    }
}
