package mk.ukim.finki.emtlabb.service.domain;

import mk.ukim.finki.emtlabb.model.domain.Accommodation;
import mk.ukim.finki.emtlabb.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationService {
    List<Accommodation> listAllAccommodationsInTemporaryReservation(String username);
    Optional<TemporaryReservation> addAccommodation(String username, Long accommodationId);
    Optional<TemporaryReservation> confirmReservation(String username);


}
