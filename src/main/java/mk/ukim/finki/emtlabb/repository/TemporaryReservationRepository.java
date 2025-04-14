package mk.ukim.finki.emtlabb.repository;

import mk.ukim.finki.emtlabb.dto.TemporaryReservationDto;
import mk.ukim.finki.emtlabb.model.domain.TemporaryReservation;
import mk.ukim.finki.emtlabb.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemporaryReservationRepository extends JpaRepository<TemporaryReservation, Long> {
    Optional<TemporaryReservation> findByUser(User user);
}
