package mk.ukim.finki.ibproekt.service.impl;

import mk.ukim.finki.ibproekt.model.Ballot;
import mk.ukim.finki.ibproekt.model.Candidate;
import mk.ukim.finki.ibproekt.model.Election;
import mk.ukim.finki.ibproekt.model.Voter;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidBallotIdException;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidCandidateIdException;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidElectionIdException;
import mk.ukim.finki.ibproekt.model.exceptions.InvalidVoterIdException;
import mk.ukim.finki.ibproekt.repository.BallotRepository;
import mk.ukim.finki.ibproekt.repository.CandidateRepository;
import mk.ukim.finki.ibproekt.repository.ElectionRepository;
import mk.ukim.finki.ibproekt.repository.VoterRepository;
import mk.ukim.finki.ibproekt.service.BallotService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BallotServiceImpl implements BallotService {
    private final BallotRepository ballotRepository;
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;
    private final ElectionRepository electionRepository;

    public BallotServiceImpl(BallotRepository ballotRepository, VoterRepository voterRepository, CandidateRepository candidateRepository, ElectionRepository electionRepository) {
        this.ballotRepository = ballotRepository;
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
        this.electionRepository = electionRepository;
    }

    @Override
    public List<Ballot> listAll() {
        return ballotRepository.findAll();
    }

    @Override
    public Ballot findById(Long id) {
        return ballotRepository.findById(id).orElseThrow(InvalidBallotIdException::new);
    }

    @Override
    public Ballot create(Long voterId, Long candidateId, Long electionId) {
        Voter voter = voterRepository.findById(voterId).orElseThrow(InvalidVoterIdException::new);
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(InvalidCandidateIdException::new);
        Election election = electionRepository.findById(electionId).orElseThrow(InvalidElectionIdException::new);
        return ballotRepository.save(new Ballot(voter,candidate,election));
    }
}
