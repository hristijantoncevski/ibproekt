package mk.ukim.finki.ibproekt.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String party;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

    public Candidate(){

    }

    public Candidate(String name, String party, String description, Election election){
        this.name = name;
        this.party = party;
        this.description = description;
        this.election = election;
    }
}
