package mk.ukim.finki.ibproekt.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @OneToMany(mappedBy = "election")
    private List<Candidate> candidates;

    public Election() {

    }

    public Election(String name, List<Candidate> candidates, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.candidates = candidates;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
