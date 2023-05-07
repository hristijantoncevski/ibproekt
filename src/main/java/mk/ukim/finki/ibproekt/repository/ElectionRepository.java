package mk.ukim.finki.ibproekt.repository;

import mk.ukim.finki.ibproekt.model.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends JpaRepository<Election,Long> {

}
