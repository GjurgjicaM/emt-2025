package mk.ukim.finki.emtlabb.service.application;

import mk.ukim.finki.emtlabb.dto.CreateCountryDto;
import mk.ukim.finki.emtlabb.dto.CreateReviewDto;
import mk.ukim.finki.emtlabb.dto.DisplayCountryDto;
import mk.ukim.finki.emtlabb.dto.DisplayReviewDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto countryDto);

    Optional<DisplayCountryDto> save(CreateCountryDto countryDto);

    void deleteById(Long id);

}
