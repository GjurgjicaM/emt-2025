package mk.ukim.finki.emtlabb.repository;

import mk.ukim.finki.emtlabb.model.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
