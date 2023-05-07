package mk.ukim.finki.ibproekt.service;

import mk.ukim.finki.ibproekt.model.Voter;

import java.util.List;

public interface VoterService {
    List<Voter> listAll();
    Voter findById(Long id);
    Voter create(String name, String username, String password);
    Voter update(Long id, String name, String username, String password);
}
