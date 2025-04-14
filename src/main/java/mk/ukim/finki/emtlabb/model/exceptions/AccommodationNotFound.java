package mk.ukim.finki.emtlabb.model.exceptions;

public class AccommodationNotFound extends RuntimeException{
    public AccommodationNotFound() {
        super("Accommodation not found");
    }
}
