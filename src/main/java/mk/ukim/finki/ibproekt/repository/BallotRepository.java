package mk.ukim.finki.ibproekt.repository;

import mk.ukim.finki.ibproekt.model.Ballot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallotRepository extends JpaRepository<Ballot,Long> {

}
