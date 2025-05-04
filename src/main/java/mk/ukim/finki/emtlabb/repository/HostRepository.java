package mk.ukim.finki.emtlabb.repository;

import mk.ukim.finki.emtlabb.model.domain.Host;
import mk.ukim.finki.emtlabb.model.projections.HostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host,Long> {
    List<HostProjection> findAllBy();
}
