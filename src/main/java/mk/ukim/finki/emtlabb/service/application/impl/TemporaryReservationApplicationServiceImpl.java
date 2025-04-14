package mk.ukim.finki.emtlabb.service.application.impl;

import mk.ukim.finki.emtlabb.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlabb.dto.TemporaryReservationDto;
import mk.ukim.finki.emtlabb.model.domain.Accommodation;
import mk.ukim.finki.emtlabb.model.domain.TemporaryReservation;
import mk.ukim.finki.emtlabb.model.domain.User;
import mk.ukim.finki.emtlabb.model.exceptions.AccommodationNotAvailable;
import mk.ukim.finki.emtlabb.model.exceptions.AccommodationNotFound;
import mk.ukim.finki.emtlabb.model.exceptions.NoTemporaryReservations;
import mk.ukim.finki.emtlabb.repository.TemporaryReservationRepository;
import mk.ukim.finki.emtlabb.service.application.TemporaryReservationApplicationService;
import mk.ukim.finki.emtlabb.service.domain.AccommodationService;
import mk.ukim.finki.emtlabb.service.domain.TemporaryReservationService;
import mk.ukim.finki.emtlabb.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationApplicationServiceImpl implements TemporaryReservationApplicationService {
    private final TemporaryReservationRepository temporaryReservationRepository;
    private final AccommodationService accommodationService;
    private final UserService userService;

    public TemporaryReservationApplicationServiceImpl(TemporaryReservationRepository temporaryReservationRepository, AccommodationService accommodationService, UserService userService) {
        this.temporaryReservationRepository = temporaryReservationRepository;
        this.accommodationService = accommodationService;
        this.userService = userService;
    }

    @Override
    public List<DisplayAccommodationDto> listAllAccommodationsInTemporaryReservation(String username) {
        User user = userService.findByUsername(username);
        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);
        return DisplayAccommodationDto.from(reservation.getAccommodations());
    }

    @Override
    public Optional<TemporaryReservationDto> addAccommodation(String username, Long accommodationId) {
        User user = userService.findByUsername(username);
        Accommodation accommodation = accommodationService.findById(accommodationId).orElseThrow(AccommodationNotFound::new);
        if (accommodation.isRented()) {
            throw new AccommodationNotAvailable();
        }

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseGet(() -> {
                    TemporaryReservation reservation1 = new TemporaryReservation();
                    reservation1.setUser(user);
                    reservation1.setAccommodations(new ArrayList<>());
                    return reservation1;
                });
        if (!reservation.getAccommodations().contains(accommodation)) {
            reservation.getAccommodations().add(accommodation);
        }

        TemporaryReservation saved = temporaryReservationRepository.save(reservation);
        return Optional.of(TemporaryReservationDto.from(saved));
    }

    @Override
    public Optional<TemporaryReservationDto> confirmReservation(String username) {
        User user = userService.findByUsername(username);

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);

        for (Accommodation accommodation : reservation.getAccommodations()) {
            accommodation.setRented(true);
            accommodationService.save(accommodation);
        }
        TemporaryReservationDto dto = TemporaryReservationDto.from(reservation);
        temporaryReservationRepository.delete(reservation);
        return Optional.of(dto);
    }
}
