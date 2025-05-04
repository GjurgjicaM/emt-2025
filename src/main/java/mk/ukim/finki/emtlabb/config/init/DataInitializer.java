package mk.ukim.finki.emtlabb.config.init;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emtlabb.model.domain.*;
import mk.ukim.finki.emtlabb.model.enumerations.Category;
import mk.ukim.finki.emtlabb.model.enumerations.Role;
import mk.ukim.finki.emtlabb.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(AccommodationRepository accommodationRepository, HostRepository hostRepository, CountryRepository countryRepository, ReviewRepository reviewRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

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

        Review rev1 = reviewRepository.save(new Review("Nice hotel", 8));
        Review rev2 = reviewRepository.save(new Review("I had a bad experience here", 3));
        Review rev3 = reviewRepository.save(new Review("It was okay", 6));


        Accommodation grandHotel = accommodationRepository.save(new Accommodation("Grand Hotel", Category.HOTEL, johnDoe, 120));
        grandHotel.getReviewList().add(rev1);
        accommodationRepository.save(grandHotel);
        Accommodation parisLoft = accommodationRepository.save(new Accommodation("Paris Loft", Category.APARTMENT, pierreDubois, 2));
        parisLoft.getReviewList().add(rev2);
        accommodationRepository.save(parisLoft);
        Accommodation berlinApp = accommodationRepository.save(new Accommodation("Berlin apartment", Category.APARTMENT, annaSchmidt, 1));
        berlinApp.getReviewList().add(rev3);
        accommodationRepository.save(berlinApp);

        userRepository.save(new User(
                "gjm",
                passwordEncoder.encode("gjm"),
                "Gjurgjica",
                "Mladenovska",
                Role.ROLE_HOST
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));

    }
}
