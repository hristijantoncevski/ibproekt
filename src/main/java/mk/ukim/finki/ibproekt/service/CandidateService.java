package mk.ukim.finki.ibproekt.service;

import mk.ukim.finki.ibproekt.model.Candidate;

import java.util.List;

public interface CandidateService {
    List<Candidate> listAll();
    Candidate findById(Long id);
    Candidate create(String name, String party, String description, Long electionId);
    Candidate update(Long id, String name, String party, String description, Long electionId);
    Candidate deleteById(Long id);
}
