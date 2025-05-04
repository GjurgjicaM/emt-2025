package mk.ukim.finki.emtlabb.service.application;

import mk.ukim.finki.emtlabb.dto.CreateCountryDto;
import mk.ukim.finki.emtlabb.dto.CreateHostDto;
import mk.ukim.finki.emtlabb.dto.DisplayCountryDto;
import mk.ukim.finki.emtlabb.dto.DisplayHostDto;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto);

    Optional<DisplayHostDto> save(CreateHostDto hostDto);

    void deleteById(Long id);

}
