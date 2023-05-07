package mk.ukim.finki.ibproekt.repository;

import mk.ukim.finki.ibproekt.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends JpaRepository<Voter,Long> {

}
