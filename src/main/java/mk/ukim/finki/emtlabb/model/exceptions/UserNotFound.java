package mk.ukim.finki.emtlabb.model.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound() {
        super("User not found");
    }
}
