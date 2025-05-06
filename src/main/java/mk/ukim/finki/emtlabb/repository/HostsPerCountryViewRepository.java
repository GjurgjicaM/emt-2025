package mk.ukim.finki.emtlabb.repository;

import mk.ukim.finki.emtlabb.model.views.HostsPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HostsPerCountryViewRepository extends JpaRepository<HostsPerCountryView,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW hosts_per_countries", nativeQuery = true)
    void refreshMaterializedView();

    @Override
    List<HostsPerCountryView>findAll();
}
