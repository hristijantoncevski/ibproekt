package mk.ukim.finki.ibproekt.service;


import mk.ukim.finki.ibproekt.model.Voter;

public interface AuthService {
    Voter login(String username, String password);
}

