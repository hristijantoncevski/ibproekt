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

    private String party;

    private Long numberOfVotes;

    public Candidate(){

    }

    public Candidate(String name, String party, Long numberOfVotes){
        this.name = name;
        this.party = party;
        this.numberOfVotes = numberOfVotes;
    }
}
