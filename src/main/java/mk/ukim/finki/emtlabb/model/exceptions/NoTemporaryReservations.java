package mk.ukim.finki.emtlabb.model.exceptions;

public class NoTemporaryReservations extends RuntimeException{
    public NoTemporaryReservations() {
        super("No temporary reservations");
    }
}
