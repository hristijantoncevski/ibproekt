package mk.ukim.finki.ibproekt.repository;

import mk.ukim.finki.ibproekt.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {

}
