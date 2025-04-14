package mk.ukim.finki.emtlabb.service.application;

import mk.ukim.finki.emtlabb.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlabb.dto.TemporaryReservationDto;
import mk.ukim.finki.emtlabb.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationApplicationService {
    List<DisplayAccommodationDto> listAllAccommodationsInTemporaryReservation(String username);
    Optional<TemporaryReservationDto> addAccommodation(String username, Long accommodationId);
    Optional<TemporaryReservationDto> confirmReservation(String username);
}
