package mk.ukim.finki.ibproekt.service.impl;

import mk.ukim.finki.ibproekt.model.Voter;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.ibproekt.repository.VoterRepository;
import mk.ukim.finki.ibproekt.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final VoterRepository voterRepository;

    public AuthServiceImpl(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @Override
    public Voter login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return voterRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }
}