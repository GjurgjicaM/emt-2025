package mk.ukim.finki.emtlabb.config.init;

import jakarta.annotation.PostConstruct;

import mk.ukim.finki.emtlabb.repository.AccommodationsPerHostViewRepository;
import mk.ukim.finki.emtlabb.repository.HostsPerCountryViewRepository;
import org.springframework.stereotype.Component;

@Component
public class MaterializedViewRefresher {
    private final AccommodationsPerHostViewRepository accommodationsPerHostViewRepository;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;

    public MaterializedViewRefresher(AccommodationsPerHostViewRepository accommodationsPerHostViewRepository, HostsPerCountryViewRepository hostsPerCountryViewRepository) {
        this.accommodationsPerHostViewRepository = accommodationsPerHostViewRepository;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
    }


    @PostConstruct
    public void init() {
        accommodationsPerHostViewRepository.refreshMaterializedView();
        hostsPerCountryViewRepository.refreshMaterializedView();
    }
}
