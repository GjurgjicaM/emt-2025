package mk.ukim.finki.emtlabb.service.domain.impl;

import mk.ukim.finki.emtlabb.events.HostEvents;
import mk.ukim.finki.emtlabb.model.domain.Host;
import mk.ukim.finki.emtlabb.model.projections.HostProjection;
import mk.ukim.finki.emtlabb.repository.HostRepository;
import mk.ukim.finki.emtlabb.repository.HostsPerCountryViewRepository;
import mk.ukim.finki.emtlabb.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;


    public HostServiceImpl(HostRepository hostRepository, HostsPerCountryViewRepository hostsPerCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return this.hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(Host host) {
        Host saved = this.hostRepository.save(host);
        this.applicationEventPublisher.publishEvent(new HostEvents(saved, "save"));
        return Optional.of(saved);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return this.hostRepository.findById(id).map(existingHost -> {
            this.refreshMaterializedView();
            if (host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if (host.getSurname() != null) {
                existingHost.setSurname(host.getSurname());
            }
            if (host.getCountry() != null) {
                existingHost.setCountry(host.getCountry());
            }

            Host saved = this.hostRepository.save(existingHost);
            this.applicationEventPublisher.publishEvent(new HostEvents(saved, "update"));
            return saved;

        });

    }

    @Override
    public void deleteById(Long id) {
        this.hostRepository.findById(id).ifPresent(host -> {
            this.hostRepository.deleteById(id);
            this.applicationEventPublisher.publishEvent(new HostEvents(host, "delete"));
        });

    }

    @Override
    public void refreshMaterializedView() {
        hostsPerCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public List<HostProjection> getAllHostNames() {
        return this.hostRepository.findAllBy();
    }
}
