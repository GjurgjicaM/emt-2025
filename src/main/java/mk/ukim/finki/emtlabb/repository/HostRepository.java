package mk.ukim.finki.emtlabb.repository;

import mk.ukim.finki.emtlabb.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host,Long> {
}
