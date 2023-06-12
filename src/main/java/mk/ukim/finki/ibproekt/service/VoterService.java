package mk.ukim.finki.ibproekt.service;

import mk.ukim.finki.ibproekt.model.Role;
import mk.ukim.finki.ibproekt.model.Voter;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface VoterService extends UserDetailsService {
    Voter findById(Long id);
    Voter create(String name, String username, String password, Role role);
}
