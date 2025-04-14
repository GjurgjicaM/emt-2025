package mk.ukim.finki.emtlabb.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TemporaryReservationNotFound extends RuntimeException {
    public TemporaryReservationNotFound(Long id) {
        super(String.format("Temporary reservation with id: %d was not found", id));
    }
}
