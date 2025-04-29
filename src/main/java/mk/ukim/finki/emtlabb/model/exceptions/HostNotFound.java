package mk.ukim.finki.emtlabb.model.exceptions;

public class HostNotFound extends RuntimeException{
    public HostNotFound() {
        super("Host not found");
    }
}
