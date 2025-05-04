package mk.ukim.finki.emtlabb.repository;

import mk.ukim.finki.emtlabb.model.domain.Accommodation;
import mk.ukim.finki.emtlabb.model.domain.Host;
import mk.ukim.finki.emtlabb.model.domain.TemporaryReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {

}
