package mk.ukim.finki.ibproekt.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String name;

    @Column(nullable = false)
    private String password;

    public Voter() {}

    public Voter(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
