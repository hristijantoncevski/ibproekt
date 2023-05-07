package mk.ukim.finki.ibproekt.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.ibproekt.model.Candidate;
import mk.ukim.finki.ibproekt.model.Election;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidCandidateIdException;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidElectionIdException;
import mk.ukim.finki.ibproekt.repository.CandidateRepository;
import mk.ukim.finki.ibproekt.repository.ElectionRepository;
import mk.ukim.finki.ibproekt.service.CandidateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final ElectionRepository electionRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository, ElectionRepository electionRepository) {
        this.candidateRepository = candidateRepository;
        this.electionRepository = electionRepository;
    }

    @Override
    public List<Candidate> listAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElseThrow(InvalidCandidateIdException::new);
    }

    @Override
    public Candidate create(String name, String party, String description, Long electionId) {
        Election election = electionRepository.findById(electionId).orElseThrow(InvalidElectionIdException::new);
        return candidateRepository.save(new Candidate(name,party,description,election));
    }

    @Override
    @Transactional
    public Candidate update(Long id, String name, String party, String description, Long electionId) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(InvalidCandidateIdException::new);
        Election election = electionRepository.findById(electionId).orElseThrow(InvalidElectionIdException::new);
        candidate.setName(name);
        candidate.setParty(party);
        candidate.setDescription(description);
        candidate.setElection(election);
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate deleteById(Long id) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(InvalidCandidateIdException::new);
        candidateRepository.delete(candidate);
        return candidate;
    }
}
