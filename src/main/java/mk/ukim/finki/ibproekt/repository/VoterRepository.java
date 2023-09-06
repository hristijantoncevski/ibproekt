package mk.ukim.finki.ibproekt.repository;

import mk.ukim.finki.ibproekt.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter,Long> {
    Optional<Voter> findByUsername(String username);
    Optional<Voter> findByUsernameAndPassword(String username, String password);
    Optional<Voter> findByCN(String CN);
}
