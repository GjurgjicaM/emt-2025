package mk.ukim.finki.emtlabb.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import mk.ukim.finki.emtlabb.model.views.AccommodationsPerHostView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface AccommodationsPerHostViewRepository extends JpaRepository<AccommodationsPerHostView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW accommodations_per_hosts", nativeQuery = true)
    void refreshMaterializedView();

    @Override
    List<AccommodationsPerHostView> findAll();
}
