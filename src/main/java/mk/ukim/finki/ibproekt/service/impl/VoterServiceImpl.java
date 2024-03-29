package mk.ukim.finki.ibproekt.service.impl;

import mk.ukim.finki.ibproekt.model.Role;
import mk.ukim.finki.ibproekt.model.Voter;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidVoterIdException;
import mk.ukim.finki.ibproekt.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.ibproekt.model.exceptions.UsernameWithThatCnAlreadyExists;
import mk.ukim.finki.ibproekt.repository.VoterRepository;
import mk.ukim.finki.ibproekt.service.VoterService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;
    private final PasswordEncoder passwordEncoder;

    public VoterServiceImpl(VoterRepository voterRepository, PasswordEncoder passwordEncoder) {
        this.voterRepository = voterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Voter findById(Long id) {
        return voterRepository.findById(id).orElseThrow(InvalidVoterIdException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return voterRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Voter create(String name, String username, String password, Role role, String CN) throws UsernameWithThatCnAlreadyExists {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if(this.voterRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        if(this.voterRepository.findByCN(CN).isPresent()) {
            throw new UsernameWithThatCnAlreadyExists(CN);
        }
        Voter voter = new Voter(name,username,passwordEncoder.encode(password),role,CN);
        return voterRepository.save(voter);
    }
}