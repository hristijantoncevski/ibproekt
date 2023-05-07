package mk.ukim.finki.ibproekt.service;

import mk.ukim.finki.ibproekt.model.Ballot;
import mk.ukim.finki.ibproekt.model.Candidate;
import mk.ukim.finki.ibproekt.model.Election;
import mk.ukim.finki.ibproekt.model.Voter;

import java.util.List;

public interface BallotService {
    List<Ballot> listAll();
    Ballot findById(Long id);
    Ballot create(Long voterId, Long candidateId, Long electionId);
}
