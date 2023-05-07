package mk.ukim.finki.ibproekt.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.ibproekt.model.Candidate;
import mk.ukim.finki.ibproekt.model.Election;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidCandidateIdException;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidElectionIdException;
import mk.ukim.finki.ibproekt.repository.CandidateRepository;
import mk.ukim.finki.ibproekt.repository.ElectionRepository;
import mk.ukim.finki.ibproekt.service.ElectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ElectionServiceImpl implements ElectionService {
    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;

    public ElectionServiceImpl(ElectionRepository electionRepository, CandidateRepository candidateRepository) {
        this.electionRepository = electionRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<Election> listAll() {
        return electionRepository.findAll();
    }

    @Override
    public Election findById(Long id) {
        return electionRepository.findById(id).orElseThrow(InvalidElectionIdException::new);
    }

    @Override
    public Election create(String name, List<Long> candidates, LocalDateTime startDate, LocalDateTime endDate) {
        List<Candidate> candidateList = candidateRepository.findAllById(candidates);
        if(candidateList.isEmpty()) throw new InvalidCandidateIdException();
        return electionRepository.save(new Election(name,candidateList,startDate,endDate));
    }

    @Override
    @Transactional
    public Election update(Long id, String name, List<Long> candidates, LocalDateTime startDate, LocalDateTime endDate) {
        List<Candidate> candidateList = candidateRepository.findAllById(candidates);
        if(candidateList.isEmpty()) throw new InvalidCandidateIdException();
        Election election = electionRepository.findById(id).orElseThrow(InvalidElectionIdException::new);
        election.setName(name);
        election.setCandidates(candidateList);
        election.setStartDate(startDate);
        election.setEndDate(endDate);
        return electionRepository.save(election);
    }

    @Override
    public Election deleteById(Long id) {
        Election election = electionRepository.findById(id).orElseThrow(InvalidElectionIdException::new);
        electionRepository.delete(election);
        return election;
    }
}
