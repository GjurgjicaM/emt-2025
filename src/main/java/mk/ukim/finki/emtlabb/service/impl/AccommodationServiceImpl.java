package mk.ukim.finki.emtlabb.service.impl;

import mk.ukim.finki.emtlabb.model.Accommodation;
import mk.ukim.finki.emtlabb.model.dto.AccommodationDto;
import mk.ukim.finki.emtlabb.repository.AccommodationRepository;
import mk.ukim.finki.emtlabb.service.AccommodationService;
import mk.ukim.finki.emtlabb.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
    }

    @Override
    public List<Accommodation> findAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return this.accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, AccommodationDto accommodation) {
        return this.accommodationRepository.findById(id)
                .map(existingAccommodation -> {
                    if(accommodation.getName() != null){
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if (accommodation.getCategory()!= null){
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if(accommodation.getHostId()!=null && hostService.findById(accommodation.getHostId()).isPresent()){
                        existingAccommodation.setHost(hostService.findById(accommodation.getHostId()).get());
                    }
                    if(accommodation.getNumRooms() != null){
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }
                    return accommodationRepository.save(existingAccommodation);
                });
    }

    @Override
    public Optional<Accommodation> save(AccommodationDto accommodation) {
        if(accommodation.getCategory() != null && hostService.findById(accommodation.getHostId()).isPresent()){
            return Optional.of(
                    accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(),
                            hostService.findById(accommodation.getHostId()).get(), accommodation.getNumRooms()))
            );
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        return accommodationRepository.findById(id).map(accommodation -> {
            accommodation.setRented(true);
            return accommodationRepository.save(accommodation);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.accommodationRepository.deleteById(id);
    }
}
