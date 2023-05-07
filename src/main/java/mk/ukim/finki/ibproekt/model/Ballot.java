package mk.ukim.finki.ibproekt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ballot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voter_id", nullable = false)
    private Voter voter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

    public Ballot() {

    }

    public Ballot(Voter voter, Candidate candidate, Election election){
        this.voter = voter;
        this.candidate = candidate;
        this.election = election;
    }
}
