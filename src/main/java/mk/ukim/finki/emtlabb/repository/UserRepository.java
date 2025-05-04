package mk.ukim.finki.emtlabb.repository;

import mk.ukim.finki.emtlabb.model.domain.User;
import mk.ukim.finki.emtlabb.model.enumerations.Role;
import mk.ukim.finki.emtlabb.model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

        @EntityGraph(
                type = EntityGraph.EntityGraphType.FETCH,
                attributePaths = {"temporaryReservations"}
        )
        @Query("SELECT u FROM User u")
        List<User> findAllWithoutTemporaryReservationsFetched();

    UserProjection findByRole(Role role);

    @Query("select u.username, u.name, u.surname from User u")
    List<UserProjection> takeUsernameAndNameAndSurnameByProjection();

}
