package mk.ukim.finki.emtlabb.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emtlabb.model.Accommodation;
import mk.ukim.finki.emtlabb.model.Country;
import mk.ukim.finki.emtlabb.model.Host;
import mk.ukim.finki.emtlabb.model.enumerations.Category;
import mk.ukim.finki.emtlabb.repository.AccommodationRepository;
import mk.ukim.finki.emtlabb.repository.CountryRepository;
import mk.ukim.finki.emtlabb.repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(AccommodationRepository accommodationRepository, HostRepository hostRepository, CountryRepository countryRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }


    @PostConstruct
    public void init() {

        Country usa = countryRepository.save(new Country("USA", "North America"));
        Country uk = countryRepository.save(new Country("UK", "Europe"));
        Country france = countryRepository.save(new Country("France", "Europe"));
        Country japan = countryRepository.save(new Country("Japan", "Asia"));
        Country germany = countryRepository.save(new Country("Germany", "Europe"));


        Host johnDoe = hostRepository.save(new Host("John", "Doe", usa));
        Host emilySmith = hostRepository.save(new Host("Emily", "Smith", uk));
        Host pierreDubois = hostRepository.save(new Host("Pierre", "Dubois", france));
        Host takashiTanaka = hostRepository.save(new Host("Takashi", "Tanaka", japan));
        Host annaSchmidt = hostRepository.save(new Host("Anna", "Schmidt", germany));

        Accommodation grandHotel = accommodationRepository.save(new Accommodation("Grand Hotel", Category.HOTEL, johnDoe, 120));
        Accommodation parisLoft = accommodationRepository.save(new Accommodation("Paris Loft", Category.APARTMENT, pierreDubois, 2));
    }
}
