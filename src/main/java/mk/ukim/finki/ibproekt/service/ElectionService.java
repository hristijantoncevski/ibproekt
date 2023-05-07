package mk.ukim.finki.ibproekt.service;

import mk.ukim.finki.ibproekt.model.Candidate;
import mk.ukim.finki.ibproekt.model.Election;

import java.time.LocalDateTime;
import java.util.List;

public interface ElectionService {
    List<Election> listAll();
    Election findById(Long id);
    Election create(String name, List<Long> candidates, LocalDateTime startDate, LocalDateTime endDate);
    Election update(Long id, String name, List<Long> candidates, LocalDateTime startDate, LocalDateTime endDate);
    Election deleteById(Long id);
}
