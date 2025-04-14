package mk.ukim.finki.emtlabb.service.domain.impl;

import mk.ukim.finki.emtlabb.model.domain.Accommodation;
import mk.ukim.finki.emtlabb.model.domain.TemporaryReservation;
import mk.ukim.finki.emtlabb.model.domain.User;
import mk.ukim.finki.emtlabb.model.exceptions.AccommodationNotAvailable;
import mk.ukim.finki.emtlabb.model.exceptions.AccommodationNotFound;
import mk.ukim.finki.emtlabb.model.exceptions.NoTemporaryReservations;
import mk.ukim.finki.emtlabb.repository.TemporaryReservationRepository;
import mk.ukim.finki.emtlabb.service.domain.AccommodationService;
import mk.ukim.finki.emtlabb.service.domain.TemporaryReservationService;
import mk.ukim.finki.emtlabb.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationServiceImpl implements TemporaryReservationService {
    private final TemporaryReservationRepository temporaryReservationRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;

    public TemporaryReservationServiceImpl(TemporaryReservationRepository temporaryReservationRepository, UserService userService, AccommodationService accommodationService) {
        this.temporaryReservationRepository = temporaryReservationRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Accommodation> listAllAccommodationsInTemporaryReservation(String username) {
        User user = userService.findByUsername(username);
        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);
        return reservation.getAccommodations();
    }

    @Override
    public Optional<TemporaryReservation> addAccommodation(String username, Long accommodationId) {
        User user = userService.findByUsername(username);
        Accommodation accommodation = accommodationService.findById(accommodationId).orElseThrow(AccommodationNotFound::new);
        if (accommodation.isRented()){
            throw new AccommodationNotAvailable();
        }

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseGet(()->{
                    TemporaryReservation reservation1 = new TemporaryReservation();
                    reservation1.setUser(user);
                    reservation1.setAccommodations(new ArrayList<>());
                    return reservation1;
                });
        if (!reservation.getAccommodations().contains(accommodation)){
            reservation.getAccommodations().add(accommodation);
        }
        return Optional.of(temporaryReservationRepository.save(reservation));
    }

    @Override
    public Optional<TemporaryReservation> confirmReservation(String username) {
        User user = userService.findByUsername(username);

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);

        for (Accommodation accommodation : reservation.getAccommodations()){
            accommodation.setRented(true);
            accommodationService.save(accommodation);
        }
        temporaryReservationRepository.delete(reservation);
        return Optional.of(reservation);
    }
}
