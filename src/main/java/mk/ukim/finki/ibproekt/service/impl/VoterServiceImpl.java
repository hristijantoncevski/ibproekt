package mk.ukim.finki.ibproekt.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.ibproekt.model.Voter;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidVoterIdException;
import mk.ukim.finki.ibproekt.repository.VoterRepository;
import mk.ukim.finki.ibproekt.service.VoterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;

    public VoterServiceImpl(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @Override
    public List<Voter> listAll() {
        return voterRepository.findAll();
    }

    @Override
    public Voter findById(Long id) {
        return voterRepository.findById(id).orElseThrow(InvalidVoterIdException::new);
    }

    @Override
    public Voter create(String name, String username, String password) {
        return voterRepository.save(new Voter(name,username,password));
    }

    @Override
    @Transactional
    public Voter update(Long id, String name, String username, String password) {
        Voter voter = voterRepository.findById(id).orElseThrow(InvalidVoterIdException::new);
        voter.setName(name);
        voter.setUsername(username);
        voter.setPassword(password);
        return voterRepository.save(voter);
    }
}